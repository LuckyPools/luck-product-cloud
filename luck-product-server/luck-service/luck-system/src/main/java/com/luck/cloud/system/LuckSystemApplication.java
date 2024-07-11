package com.luck.cloud.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 系统服务
 * @author luck
 * @date 2024/7/4
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.luck.cloud")
public class LuckSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(LuckSystemApplication.class, args);
    }
}
