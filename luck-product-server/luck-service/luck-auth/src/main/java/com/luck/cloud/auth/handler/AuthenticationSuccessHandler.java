package com.luck.cloud.auth.handler;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luck.cloud.common.utils.JwtUtils;
import com.luck.cloud.core.entity.LoginUser;
import com.luck.cloud.redis.utils.RedisCacheUtils;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private  static ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        Map<String, Object> map = new HashMap<>(2);
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        if(!RedisCacheUtils.exists("user:" + loginUser.getId())){
            map.put("userId", loginUser.getId());
            map.put("username", loginUser.getUsername());
            // 有效时长12小时
            long jwtTokenExpired = 12 * 60 * 60 * 1000;
            String token = JwtUtils.generateToken(map, loginUser.getUsername(), jwtTokenExpired);
            log.info("用户" + loginUser.getUsername() + "登录成功");
            RedisCacheUtils.put("user:" + loginUser.getId(), JSON.toJSONString(loginUser), jwtTokenExpired);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(token));
        }else{
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("用户已登录");
        }
    }

}
