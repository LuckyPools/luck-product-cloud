package com.luck.cloud.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器
 *
 * @author luck
 * @date 2024-07-17
 */
@RestController
public class LoginController {


    @PostMapping
    public String login() {
        // todo 将信息存储进
        return "success";
    }
}
