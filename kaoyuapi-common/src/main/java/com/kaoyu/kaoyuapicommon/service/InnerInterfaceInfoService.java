package com.kaoyu.kaoyuapicommon.service;


import com.kaoyu.kaoyuapicommon.model.entity.InterfaceInfo;

/**
 *
 */
public interface InnerInterfaceInfoService {



    /**
     * 从数据库中查询模拟接口是否存在
     * @param path
     * @param method
     * @return
     */
    InterfaceInfo getInterfaceInfo(String path,String method);
}
