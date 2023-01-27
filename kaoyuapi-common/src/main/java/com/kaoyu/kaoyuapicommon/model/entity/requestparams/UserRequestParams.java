package com.kaoyu.kaoyuapicommon.model.entity.requestparams;

import lombok.Data;

import java.io.Serializable;

/**
 * @Classname UserRequestParams
 * @Description TODO
 * @Date 2023/1/27 12:42
 * @Created by dylan
 */
@Data
public class UserRequestParams implements Serializable {
    private String username;
}
