package com.kaoyu.kaoyuapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import com.kaoyu.kaoyuapiclientsdk.utils.SignUtil;
import com.kaoyu.kaoyuapicommon.model.entity.requestparams.ImageRequestParams;
import com.kaoyu.kaoyuapicommon.model.entity.requestparams.UserRequestParams;
import com.kaoyu.kaoyuapicommon.model.entity.requestparams.WallpaperRequestParams;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: cky
 * @Date: 2023/1/14 21:31
 * @Description: 调用第三方接口的客户端
 */
public class KaoYuApiClient {

    private String accessKey;
    private String secretKey;
    private final String GATEWAY_HOST = "http://127.0.0.1:8082";
    public final static String RANDOM_IMAGE_URL = "http://shibe.online/api/shibes";
    public final static String RANDOM_WALLPAPER_URL = "http://shibe.online/api/shibes";

    public KaoYuApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.get(GATEWAY_HOST + "/api/name/", paramMap);
        System.out.println("result = " + result);
        return result;
    }


    public String getNameByPost(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.post(GATEWAY_HOST + "/api/name/", paramMap);
        System.out.println("result = " + result);
        return result;
    }

    private Map<String, String> getHeaderMap(String body) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        hashMap.put("body", body);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", SignUtil.getSign(body, secretKey));
//        hashMap.put("secretKey",secretKey);
        return hashMap;
    }

    public String getUserNameByPost(UserRequestParams user) {
        String json = JSONUtil.toJsonStr(user);
        String url = GATEWAY_HOST + "/api/name/user";
        HttpResponse response = HttpRequest.post(url)
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        System.out.println("response.getStatus() = " + response.getStatus());
        String result = response.body();
        System.out.println("result = " + result);
        return result;
    }

    public String randomImages(ImageRequestParams params) {
        String url = GATEWAY_HOST + "/api/image/random";
        if (params == null) {
            params = new ImageRequestParams();
            params.setCount("1");
        }
        return getRequestResult(params, url);
    }

    public String randomWallpaper(WallpaperRequestParams params) {
        String url = GATEWAY_HOST + "/api/wallpaper/random";
        if (params == null) {
            params = new WallpaperRequestParams();
            params.setMethod("pc");
            params.setFormat("json");
            params.setLx("fengjing");
        }
        return getRequestResult(params, url);
    }

    public String getRequestResult(Object params, String url) {
        String body = new Gson().toJson(params);
        return HttpRequest.get(url).addHeaders(getHeaderMap(body)).execute().body();
    }

}
