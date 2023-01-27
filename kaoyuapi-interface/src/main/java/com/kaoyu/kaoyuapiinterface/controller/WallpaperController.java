package com.kaoyu.kaoyuapiinterface.controller;

import cn.hutool.http.HttpRequest;
import com.google.gson.Gson;
import com.kaoyu.kaoyuapicommon.model.entity.requestparams.WallpaperRequestParams;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname Wallpaper
 * @Description TODO
 * @Date 2023/1/27 12:28
 * @Created by dylan
 */
@RestController
@RequestMapping("/wallpaper")
public class WallpaperController {
    public final static String RANDOM_IMAGE_URL = "https://api.btstu.cn/sjbz/api.php";

    @GetMapping("/random")
    public String randomWallpaper(WallpaperRequestParams params, HttpServletRequest request) {
        String json = request.getHeader("body");
        WallpaperRequestParams imageRequestParams = new Gson().fromJson(json, WallpaperRequestParams.class);
        String body = HttpRequest.get(RANDOM_IMAGE_URL
                + "?method="
                + imageRequestParams.getMethod()
                + "&format="
                + imageRequestParams.getFormat()
                + "&lx="
                + imageRequestParams.getLx()
        ).execute().body();
//        {"code":"200","imgurl":"https:\/\/img.btstu.cn\/api\/images\/5c9ae72e5cb27.jpg","width":"1920","height":"1080"}
        char[] chars = body.toCharArray();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '\\'){
                result.append(chars[i]);
            }
        }
        return result.toString();
    }
}
