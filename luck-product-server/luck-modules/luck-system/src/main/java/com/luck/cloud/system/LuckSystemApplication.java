package com.luck.cloud.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 系统服务启动类
 * @author liul
 * @Date 2024/7/4
 */
@SpringBootApplication(scanBasePackages = "com.luck.cloud")
public class LuckSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(LuckSystemApplication.class, args);
    }
}
