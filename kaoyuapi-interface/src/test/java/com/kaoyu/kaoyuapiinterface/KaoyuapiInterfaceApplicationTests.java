package com.kaoyu.kaoyuapiinterface;

import com.kaoyu.kaoyuapiclientsdk.client.KaoYuApiClient;
import com.kaoyu.kaoyuapiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KaoyuapiInterfaceApplicationTests {

    @Autowired
    KaoYuApiClient client;
    @Test
    void contextLoads() {
        System.out.println("client.getNameByGet(\"kaoyu\") = " + client.getNameByGet("kaoyu"));
        System.out.println("client.getNameByPost(\"kaoyu\") = " + client.getNameByPost("kaoyu"));
        User user = new User();
        user.setUsername("kaoyu");
        System.out.println("client.getUserNameByPost(user) = " + client.getUserNameByPost(user));
    }

}
