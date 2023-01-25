package com.kaoyu.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaoyu.kaoyuapicommon.model.entity.InterfaceInfo;
import com.kaoyu.project.common.ErrorCode;
import com.kaoyu.project.exception.BusinessException;
import com.kaoyu.project.mapper.InterfaceInfoMapper;
import com.kaoyu.project.service.InterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *
 */
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService {

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long id = interfaceInfo.getId();
        String name = interfaceInfo.getName();
        String description = interfaceInfo.getDescription();
        String method = interfaceInfo.getMethod();
        String url = interfaceInfo.getUrl();
        String requestHeader = interfaceInfo.getRequestHeader();
        String responseHeader = interfaceInfo.getResponseHeader();
        Integer status = interfaceInfo.getStatus();
        Long userId = interfaceInfo.getUserId();
        Date createTime = interfaceInfo.getCreateTime();
        Date updateTime = interfaceInfo.getUpdateTime();
        Byte isDeleted = interfaceInfo.getIsDeleted();

        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(name) ) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(name)  && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称过长");
        }
    }

}




