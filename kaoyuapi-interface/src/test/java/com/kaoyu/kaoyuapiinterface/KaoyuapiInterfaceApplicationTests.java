package com.kaoyu.kaoyuapiinterface;

import cn.hutool.http.HttpRequest;
import com.kaoyu.kaoyuapiclientsdk.client.KaoYuApiClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KaoyuapiInterfaceApplicationTests {

    @Autowired
    KaoYuApiClient client;
    @Test
    void contextLoads() {
        System.out.println(HttpRequest.get("https://api.btstu.cn/sjbz/api.php?method=pc&format=images&lx=fengjing"));
    }

}
