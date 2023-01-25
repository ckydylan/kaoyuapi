package com.kaoyu.kaoyuapiclientsdk;

import com.kaoyu.kaoyuapiclientsdk.client.KaoYuApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: cky
 * @Date: 2023/1/14 23:06
 * @Description:
 */
@Configuration
@ConfigurationProperties("kaoyuapi.client")
@Data
@ComponentScan
public class KaoYuApiClientConfig {
    private String accessKey;
    private String secretKey;

    @Bean
    public KaoYuApiClient kaoYuApiClient(){
        return new KaoYuApiClient(accessKey,secretKey);
    }
}
