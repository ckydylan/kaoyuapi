package com.kaoyu.kaoyuapicommon.model.entity.requestparams;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname WallpaperRequestParams
 * @Description TODO
 * @Date 2023/1/27 12:34
 * @Created by dylan
 */
@Data
public class WallpaperRequestParams implements Serializable {
    private String method;
    private String lx;
    private String format;
}
