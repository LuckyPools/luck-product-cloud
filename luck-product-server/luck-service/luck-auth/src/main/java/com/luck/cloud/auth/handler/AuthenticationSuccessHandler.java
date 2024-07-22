package com.luck.cloud.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luck.cloud.auth.entity.LoginUser;
import com.luck.cloud.common.utils.JwtUtils;
import com.luck.cloud.redis.utils.RedisCacheUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证成功处理
 * @author luck
 * @date 2021/3/11 15:00
 */
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /**
     * token 过期时间
     */
    @Value("${jwt.token.expired}")
    private long jwtTokenExpired;

    private  static ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        Map<String, Object> map = new HashMap<>(2);
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        if(!RedisCacheUtils.exists("user:" + loginUser.getId())){
            map.put("userId", loginUser.getId());
            map.put("username", loginUser.getUsername());
            String token = JwtUtils.generateToken(map, loginUser.getUsername(), jwtTokenExpired);
            RedisCacheUtils.put("user:" + loginUser.getId(), loginUser, jwtTokenExpired);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(token));
        }else{
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("用户已登录");
        }
    }

}
