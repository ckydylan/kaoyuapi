package com.kaoyu.kaoyuapiinterface.controller;

import com.kaoyu.kaoyuapiclientsdk.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: cky
 * @Date: 2023/1/14 20:45
 * @Description: 查询名称api
 */
@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/get")
    public String getNameByGet(String name, HttpServletRequest request) {
//        String accessKey = request.getHeader("accessKey");
//        String secretKey = request.getHeader("secretKey");
//        if (!accessKey.equals("cky") || !secretKey.equals("abc")) {
//            throw new RuntimeException("无权限");
//        }
        System.out.println(request.getHeader("kaoyu"));
        return "GET 你的名字是 : " + name;
    }

    @PostMapping("/post")
    public String getNameByPost(@RequestParam String name) {
        return "POST 你的名字是 : " + name;
    }

    @PostMapping("/user")
    public String getUserNameByPost(@RequestBody User user, HttpServletRequest request) {
//        String accessKey = request.getHeader("accessKey");
//        String nonce = request.getHeader("nonce");
//        String timestamp = request.getHeader("timestamp");
//        String sign = request.getHeader("sign");
//        String body = request.getHeader("body");
//
//        if (!accessKey.equals("cky")) {
//            throw new RuntimeException("无权限");
//        }
//        if (Long.parseLong(nonce) > 10000){
//            throw new RuntimeException("无权限");
//        }
//        String serverSign = SignUtil.getSign(body, "abc");
//        if (!sign.equals(serverSign)){
//            throw new RuntimeException("无权限");
//        }
        String result =  "POST 用户名是 : " + user.getUsername();
        return result;
    }

}
