package com.kaoyu.project.model.vo;

import com.kaoyu.kaoyuapicommon.model.entity.InterfaceInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: cky
 * @Date: 2023/1/18 19:49
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InterfaceInfoVO extends InterfaceInfo {

    private Integer totalNum;

}
