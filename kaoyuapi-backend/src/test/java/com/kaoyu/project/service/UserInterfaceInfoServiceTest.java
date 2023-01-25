package com.kaoyu.project.service;


import com.kaoyu.kaoyuapicommon.service.InnerUserInterfaceInfoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserInterfaceInfoServiceTest {
    @Resource
    InnerUserInterfaceInfoService service;

    @Test
    public void invokeCount() {
        final boolean b = service.invokeCount(1, 3);
        Assertions.assertTrue(b);
    }
}

