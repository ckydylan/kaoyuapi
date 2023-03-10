package com.kaoyu.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kaoyu.kaoyuapicommon.model.entity.UserInterfaceInfo;

import java.util.List;

/**
 * @Entity generator.domain.UserInterfaceInfo
 */
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {

    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);
}




