package com.luck.cloud.eureka;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableEurekaServer
public class LuckEurekaApplication {

    public static void main(String[] args){
        SpringApplication.run(LuckEurekaApplication.class,args);
    }

    /**
     * 禁用csrf保护
     */
    @EnableWebSecurity
    public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter{
        protected void configure(HttpSecurity http) throws Exception{
            http.csrf().disable();
            super.configure(http);
        }
    }

}
