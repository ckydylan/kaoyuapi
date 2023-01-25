package com.kaoyu.kaoyuapicommon.service;

import com.kaoyu.kaoyuapicommon.model.entity.User;


/**
 * 用户服务
 *
 * @author kaoyu
 */
public interface InnerUserService  {

    /**
     * 从数据库中查是否已分配给用户密钥（ak sk ）
     *
     * @param accessKey
     * @param
     * @return
     */
    User getInvokeUser(String accessKey);
}
