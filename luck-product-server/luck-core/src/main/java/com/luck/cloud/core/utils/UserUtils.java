package com.luck.cloud.core.utils;


import cn.hutool.core.map.MapUtil;
import com.luck.cloud.common.utils.JwtUtils;
import com.luck.cloud.common.utils.StringUtils;
import com.luck.cloud.core.dto.LoginUser;
import com.luck.cloud.core.entity.Role;
import com.luck.cloud.redis.utils.RedisCacheUtils;
import io.jsonwebtoken.Claims;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户工具
 * @author luck
 * @Date 2024/8/1
 */
public class UserUtils {

    /**
     * 获取用户
     * @return
     */
    public static LoginUser getUser(){
        String token = getUserToken();
        if(StringUtils.isNoneBlank(token)){
            Claims claims = JwtUtils.getClaims("token");
            String userId = MapUtil.getStr(claims, "userId");
            LoginUser loginUser = RedisCacheUtils.get("user:" + userId, LoginUser.class);
            return loginUser;
        }
        return null;
    }

    /**
     * 获取用户ID
     * @return
     */
    public static String getUserId(){
        LoginUser loginUser = getUser();
        if(loginUser != null){
            return getUser().getId();
        }
        return null;
    }

    public static List<Role> getUserRoles(){
        return null;
    }

    public static List<Role> getUserMenus(){
        return null;
    }

    public static void removeUserCache(){
        String token = getUserToken();
        if(StringUtils.isNoneBlank(token)) {
            Claims claims = JwtUtils.getClaims("token");
            String userId = MapUtil.getStr(claims, "userId");
            RedisCacheUtils.remove("user:" + userId);
        }
    }

    private static String getUserToken(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader("Authorization");
    }
}
