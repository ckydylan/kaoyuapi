package com.kaoyu.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.kaoyu.kaoyuapicommon.model.entity.InterfaceInfo;
import com.kaoyu.kaoyuapicommon.model.entity.UserInterfaceInfo;
import com.kaoyu.project.annotation.AuthCheck;
import com.kaoyu.project.common.BaseResponse;
import com.kaoyu.project.common.ErrorCode;
import com.kaoyu.project.common.ResultUtils;
import com.kaoyu.project.constant.UserConstant;
import com.kaoyu.project.exception.BusinessException;
import com.kaoyu.project.model.vo.InterfaceInfoVO;
import com.kaoyu.project.service.AnalysisUserInterfaceInfoService;
import com.kaoyu.project.service.InterfaceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: cky
 * @Date: 2023/1/18 19:47
 * @Description:
 */
@RestController
@RequestMapping("/analysis")
@Slf4j
public class AnalysisController {
    @Resource
    private AnalysisUserInterfaceInfoService analysisUserInterfaceInfoService;
    @Resource
    private InterfaceInfoService interfaceInfoService;

    @GetMapping("/top/interface/invoke")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<InterfaceInfoVO>> listTopInvokeInterfaceInfo(int limit) {
        List<UserInterfaceInfo> userInterfaceInfos = analysisUserInterfaceInfoService.listTopInvokeInterfaceInfo(limit);
        Map<Long, List<UserInterfaceInfo>> interfaceInfoIdObjMap = userInterfaceInfos.stream().collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",interfaceInfoIdObjMap.keySet());
        List<InterfaceInfo> list = interfaceInfoService.list(queryWrapper);
        if (CollectionUtils.isEmpty(list)){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        final List<InterfaceInfoVO> interfaceInfoVOList = list.stream().map(interfaceInfo -> {
            InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
            BeanUtils.copyProperties(interfaceInfo, interfaceInfoVO);
            int totalNum = interfaceInfoIdObjMap.get(interfaceInfo.getId()).get(0).getTotalNum();
            interfaceInfoVO.setTotalNum(totalNum);
            return interfaceInfoVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(interfaceInfoVOList);
    }
}
