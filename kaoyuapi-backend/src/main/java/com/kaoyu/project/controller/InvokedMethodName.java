package com.kaoyu.project.controller;

/**
 * @Classname InvokedMethodName
 * @Description 接口方法名
 * @Date 2023/1/26 15:00
 * @Created by dylan
 */
public enum InvokedMethodName {
    GET_USERNAME_BY_POST("获取登陆用户名", "getUserNameByPost"),
    GET_RANDOM_IMAGES("获取随机图片", "getRandomImages"),
    GET_RANDOM_WALLPAPER("获取随机壁纸", "getRandomWallpaper");

    private final String text;

    private final String value;

    InvokedMethodName(String text, String value) {
        this.text = text;
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public static InvokedMethodName getByValue(String value) {
        for (InvokedMethodName name : values()) {
            if (name.getValue().equals(value)) {
                return name;
            }
        }
        return null;
    }
}
