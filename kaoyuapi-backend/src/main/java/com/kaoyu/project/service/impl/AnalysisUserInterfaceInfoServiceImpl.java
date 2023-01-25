package com.kaoyu.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaoyu.kaoyuapicommon.model.entity.UserInterfaceInfo;
import com.kaoyu.project.mapper.UserInterfaceInfoMapper;
import com.kaoyu.project.service.AnalysisUserInterfaceInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Service
public class AnalysisUserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
        implements AnalysisUserInterfaceInfoService {
    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Override
    public List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit) {
        return userInterfaceInfoMapper.listTopInvokeInterfaceInfo(limit);
    }
}




