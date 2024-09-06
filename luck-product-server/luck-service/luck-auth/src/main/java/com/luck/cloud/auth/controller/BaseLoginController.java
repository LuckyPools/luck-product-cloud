package com.luck.cloud.auth.controller;

import com.luck.cloud.core.utils.UserUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器
 *
 * @author luck
 * @date 2024-07-17
 */
@RestController
public class BaseLoginController {


    @PostMapping
    public String login() {
        return "login success";
    }


    @PostMapping("/logiut")
    public String logout() {
        UserUtils.removeUserCache();
        return "logout success";
    }
}
