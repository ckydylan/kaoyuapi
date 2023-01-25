package com.kaoyu.project.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaoyu.kaoyuapicommon.model.entity.UserInterfaceInfo;
import com.kaoyu.project.common.ErrorCode;
import com.kaoyu.project.exception.BusinessException;
import com.kaoyu.project.mapper.UserInterfaceInfoMapper;
import com.kaoyu.project.service.UserInterfaceInfoService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *
 */
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
        implements UserInterfaceInfoService {

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long id = userInterfaceInfo.getId();
        Long userId = userInterfaceInfo.getUserId();
        Long interfaceInfoId = userInterfaceInfo.getInterfaceInfoId();
        Integer totalNum = userInterfaceInfo.getTotalNum();
        Integer leftNum = userInterfaceInfo.getLeftNum();
        Integer status = userInterfaceInfo.getStatus();
        Date createTime = userInterfaceInfo.getCreateTime();
        Date updateTime = userInterfaceInfo.getUpdateTime();
        Byte isDeleted = userInterfaceInfo.getIsDeleted();


        // 创建时，所有参数必须非空
        if (add) {
            if (interfaceInfoId <= 0 || userId <= 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口或用户不存在");
            }
        }
        if (leftNum < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余次数不能小于0");
        }
    }



    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        if (interfaceInfoId<=0 || userId<=0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        final UpdateWrapper<UserInterfaceInfo> userInterfaceInfoUpdateWrapper = new UpdateWrapper<>();
        userInterfaceInfoUpdateWrapper.eq("interfaceInfoId",interfaceInfoId);
        userInterfaceInfoUpdateWrapper.eq("userId",userId);
        userInterfaceInfoUpdateWrapper.gt("leftNum",0);
        userInterfaceInfoUpdateWrapper.setSql("leftNum = leftNum - 1,totalNum = totalNum + 1");
        return this.update(userInterfaceInfoUpdateWrapper);
    }



}




