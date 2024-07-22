package com.luck.cloud.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luck.cloud.base.vo.ResultVO;
import com.luck.cloud.common.enums.AuthCodeEnum;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理
 * @author luck
 * @date 2021/3/11 15:14
 */
@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private  static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        ResultVO resultVO = ResultVO.error();
        // 账号不存在
        if (exception instanceof UsernameNotFoundException) {
            resultVO = ResultVO.error(AuthCodeEnum.ACCOUNT_NOT_EXIST.getCode());
            // 用户名或密码错误
        } else if (exception instanceof BadCredentialsException) {
            resultVO = ResultVO.error(AuthCodeEnum.LOGIN_PASSWORD_ERROR);
            // 账号已过期
        } else if (exception instanceof AccountExpiredException) {
            resultVO = ResultVO.error(AuthCodeEnum.ACCOUNT_EXPIRED);
            // 账号已被锁定
        } else if (exception instanceof LockedException) {
            resultVO = ResultVO.error(AuthCodeEnum.ACCOUNT_LOCKED);
            // 用户凭证已失效
        } else if (exception instanceof CredentialsExpiredException) {
            resultVO = ResultVO.error(AuthCodeEnum.ACCOUNT_CREDENTIAL_EXPIRED);
            // 账号已被禁用
        } else if (exception instanceof DisabledException) {
            resultVO = ResultVO.error(AuthCodeEnum.ACCOUNT_DISABLE);
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(resultVO));
    }


}
