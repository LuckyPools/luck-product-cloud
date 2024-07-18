package com.luck.cloud.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
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
        return "success";
    }
}
