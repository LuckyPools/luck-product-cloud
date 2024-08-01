package com.luck.cloud.auth.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 登录异常
 * @author luck
 * @Date 2024/7/20
 */
public class LoginException extends AuthenticationException {

    public LoginException(String msg, Throwable t) {
        super(msg, t);
    }

    public LoginException(String msg) {
        super(msg);
    }
}
