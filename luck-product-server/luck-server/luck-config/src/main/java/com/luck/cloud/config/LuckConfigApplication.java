package com.luck.cloud.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;


/**
 * 配置中心
 *
 * @author luck
 * @date 2024-07-11
 */
@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class LuckConfigApplication {
    public static void main(String[] args){
        SpringApplication.run(LuckConfigApplication.class,args);
    }
}
