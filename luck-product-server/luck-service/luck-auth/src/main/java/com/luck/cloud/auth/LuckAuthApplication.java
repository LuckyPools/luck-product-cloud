package com.luck.cloud.auth;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 验证服务
 * @author luck
 * @date 2024/7/16
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.luck.cloud")
public class LuckAuthApplication {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
