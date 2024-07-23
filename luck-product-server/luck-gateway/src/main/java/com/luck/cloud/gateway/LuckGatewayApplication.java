package com.luck.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 网关
 *
 * @author luck
 * @date 2024-07-11
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.luck.cloud")
public class LuckGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(LuckGatewayApplication.class,args);
    }
}
