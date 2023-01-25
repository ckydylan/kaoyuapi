package com.kaoyu.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kaoyu.kaoyuapicommon.model.entity.UserInterfaceInfo;

import java.util.List;

public interface AnalysisUserInterfaceInfoService extends IService<UserInterfaceInfo> {
    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);
}
