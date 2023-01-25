package com.kaoyu.kaoyuapigateway;

import com.kaoyu.project.provider.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableDubbo
@Service

public class KaoyuapiGatewayApplication {

    @DubboReference
    private DemoService demoService;

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(KaoyuapiGatewayApplication.class, args);
        KaoyuapiGatewayApplication application = context.getBean(KaoyuapiGatewayApplication.class);
        String result = application.doSayHello("world");
        System.out.println("result: " + result);
    }

    public String doSayHello(String name) {
        return demoService.sayHello(name);
    }
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("tobaidu", r -> r.path("/baidu")
//                        .uri("https://www.baidu.com"))
//                .route("toyupiicu", r -> r.host("/yupiicu")
//                        .uri("http://yupi.icu"))
//                .route("rewrite_route", r -> r.host("*.rewrite.org")
//                        .filters(f -> f.rewritePath("/foo/(?<segment>.*)", "/${segment}"))
//                        .uri("http://httpbin.org"))
//                .route("hystrix_route", r -> r.host("*.hystrix.org")
//                        .filters(f -> f.hystrix(c -> c.setName("slowcmd")))
//                        .uri("http://httpbin.org"))
//                .route("hystrix_fallback_route", r -> r.host("*.hystrixfallback.org")
//                        .filters(f -> f.hystrix(c -> c.setName("slowcmd").setFallbackUri("forward:/hystrixfallback")))
//                        .uri("http://httpbin.org"))
//                .route("limit_route", r -> r
//                        .host("*.limited.org").and().path("/anything/**")
//                        .filters(f -> f.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter())))
//                        .uri("http://httpbin.org"))
//                .build();
//    }

}
