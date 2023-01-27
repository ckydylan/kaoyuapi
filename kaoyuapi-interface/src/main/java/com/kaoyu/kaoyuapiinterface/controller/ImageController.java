package com.kaoyu.kaoyuapiinterface.controller;

import cn.hutool.http.HttpRequest;
import com.google.gson.Gson;
import com.kaoyu.kaoyuapicommon.model.entity.requestparams.ImageRequestParams;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @Classname ImageController
 * @Description 图片api
 * @Date 2023/1/25 21:47
 * @Created by dylan
 */
@RestController
@RequestMapping("/image")
public class ImageController {
    public final static String RANDOM_IMAGE_URL = "http://shibe.online/api/shibes";

    @GetMapping("/random")
    public String randomImages(ImageRequestParams image, HttpServletRequest request) {
        String json = request.getHeader("body");
        ImageRequestParams imageRequestParams = new Gson().fromJson(json, ImageRequestParams.class);
        String body = HttpRequest.get(RANDOM_IMAGE_URL
                + "?count="
                + imageRequestParams.getCount()).execute().body();
        Gson gson = new Gson();
        String[] s = gson.fromJson(body, String[].class);
        HashMap<String,String> map = new HashMap<>();
        map.put("imgurl", s[0]);
        return gson.toJson(map);
    }
}
