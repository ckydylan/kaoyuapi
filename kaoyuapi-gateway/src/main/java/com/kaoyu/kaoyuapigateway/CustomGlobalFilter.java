package com.kaoyu.kaoyuapigateway;

import com.kaoyu.kaoyuapiclientsdk.utils.SignUtil;
import com.kaoyu.kaoyuapicommon.model.entity.InterfaceInfo;
import com.kaoyu.kaoyuapicommon.model.entity.User;
import com.kaoyu.kaoyuapicommon.service.InnerInterfaceInfoService;
import com.kaoyu.kaoyuapicommon.service.InnerUserInterfaceInfoService;
import com.kaoyu.kaoyuapicommon.service.InnerUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全局过滤
 */
@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @DubboReference
    InnerUserService userService;
    @DubboReference
    InnerUserInterfaceInfoService userInterfaceInfoService;
    @DubboReference
    InnerInterfaceInfoService interfaceInfoService;
    private final static List<String> IP_WHITE_LIST = Arrays.asList("127.0.0.1", "127.0.0.1");
    private final String INTERFACE_HOST="http://127.0.0.1:8001";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        1.请求日志
        final ServerHttpRequest request = exchange.getRequest();
        log.info("请求唯一标识 " + request.getId());
        final String path = INTERFACE_HOST+request.getPath().value();
        log.info("请求路径 " + path);
        final String method = request.getMethod().toString();
        log.info("请求方法 " + method);
        log.info("请求参数" + request.getQueryParams());
        final String sourceAddress = request.getLocalAddress().getHostString();
        log.info("请求来源地址" + sourceAddress);
        log.info("请求来源地址" + request.getRemoteAddress());
        final ServerHttpResponse response = exchange.getResponse();

//        2.访问控制-黑白名单
        if (!IP_WHITE_LIST.contains(sourceAddress)) {
            return handlerNoAuth(response);
        }
//        3.用户鉴权（判断ak，sk是否合法）
        final HttpHeaders headers = request.getHeaders();
        String accessKey = headers.getFirst("accessKey");
        String nonce = headers.getFirst("nonce");
        String timestamp = headers.getFirst("timestamp");
        String sign = headers.getFirst("sign");
        String body = headers.getFirst("body");
        User invokeUser = null;
        try {
            invokeUser = userService.getInvokeUser(accessKey);
        } catch (Exception e) {
            log.error("getInvokeUser error", e);
        }
        if (invokeUser == null) {
            return handlerNoAuth(response);
        }
//        if (!"cky".equals(accessKey)) {
//            return handlerNoAuth(response);
//        }
        if (Long.parseLong(nonce) > 10000) {
            return handlerNoAuth(response);
        }
        //时间和当前时间不超过5分钟
        final long currentTime = System.currentTimeMillis() / 1000;
        final Long FIVE_MINUTES = 60 * 5L;
        if ((currentTime - Long.parseLong(timestamp)) >= FIVE_MINUTES) {
            return handlerNoAuth(response);
        }
        String secretKey = invokeUser.getSecretKey();
        String serverSign = SignUtil.getSign(body, secretKey);
        if (sign == null || !sign.equals(serverSign)) {
            return handlerNoAuth(response);
        }
//        4.请求的模拟接口是否存在
        InterfaceInfo interfaceInfo = null;
        try {
            interfaceInfo = interfaceInfoService.getInterfaceInfo(path, method);
        } catch (Exception e) {
            log.error("getInterfaceInfo error", e);
        }
        if (interfaceInfo == null) {
            return handlerNoAuth(response);
        }
        //todo 是否有调用次数
//        5.请求转发，调用模拟接口
//        6.响应日志

//        final Mono<Void> filter = chain.filter(exchange);
        log.info("响应:" + response.getStatusCode());
        return handleResponseLog(exchange, chain,interfaceInfo.getId(),interfaceInfo.getUserId());
    }

    /**
     * 处理响应
     *
     * @param exchange
     * @param chain
     * @return
     */
    public Mono<Void> handleResponseLog(ServerWebExchange exchange, GatewayFilterChain chain,long interfaceInfoId,long userId) {
        try {
            ServerHttpResponse originalResponse = exchange.getResponse();
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();

            HttpStatus statusCode = originalResponse.getStatusCode();

            if (statusCode == HttpStatus.OK) {
                ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {

                    @Override
                    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                        log.info("body instanceof Flux: {}", (body instanceof Flux));
                        if (body instanceof Flux) {
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            //
                            return super.writeWith(fluxBody.map(dataBuffer -> {
                                // 7.调用成功，接口调用次数 + 1
                                try {
                                    userInterfaceInfoService.invokeCount(interfaceInfoId,userId);
                                } catch (Exception e) {
                                    log.error(" invokeCount error"+e);
                                }
                                byte[] content = new byte[dataBuffer.readableByteCount()];
                                dataBuffer.read(content);
                                DataBufferUtils.release(dataBuffer);//释放掉内存
                                // 构建日志
                                StringBuilder sb2 = new StringBuilder(200);
                                List<Object> rspArgs = new ArrayList<>();
                                rspArgs.add(originalResponse.getStatusCode());
                                //rspArgs.add(requestUrl);
                                String data = new String(content, StandardCharsets.UTF_8);//data
                                sb2.append(data);
                                log.info("响应结果: " + data);
                                return bufferFactory.wrap(content);
                            }));
                        } else {
                            //        8.调用失败，返回规范错误码
//                            handlerInvokeError(response);
                            log.error("<--- {} 响应code异常", getStatusCode());
                        }
                        return super.writeWith(body);
                    }
                };
                return chain.filter(exchange.mutate().response(decoratedResponse).build());
            }
            return chain.filter(exchange);//降级处理返回数据
        } catch (Exception e) {
            log.error("网关处理响应异常: " + e);
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return -1;
    }

    public Mono<Void> handlerNoAuth(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }

    public Mono<Void> handlerInvokeError(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return response.setComplete();
    }
}
