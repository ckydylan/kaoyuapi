package com.kaoyu.project.service.impl.inner;

import com.kaoyu.kaoyuapicommon.service.InnerUserInterfaceInfoService;
import com.kaoyu.project.service.UserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @Author: cky
 * @Date: 2023/1/18 13:43
 * @Description:
 */
@DubboService

public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    UserInterfaceInfoService service;

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        return service.invokeCount(interfaceInfoId, userId);
    }
}
