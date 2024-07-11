package com.luck.cloud.common.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * JWT工具类
 * @author lr
 * @since 2021-01-09
 */
public class JwtUtils {

    /**
     * 加密私钥，用于加密信息
     */
    private static final String secret = "luck-product";

    /**
     * @param username 用户名
     * @param uuid
     * @return
     */
    public String createToken(String username, String uuid) {
        return JWT.create()
                .withClaim("username", username)
                .withClaim("uuid", uuid)
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 秘钥8
     * @param username 用户名
     * @return
     */
    public String createToken(String username, String uuid,Integer type,String tenantCode) {
        return JWT.create()
                .withClaim("username", username)
                .withClaim("uuid", uuid)
                .withClaim("type", type)
                .withClaim("tenant", tenantCode)
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 根据用户名、角色、权限、菜单等信息生成token
     * @param username 用户名
     * @param roles 角色
     * @param authorities 权限
     * @param menus 菜单
     * @return
     */
    public String createToken(String username, List<String> roles, List<String> authorities, List<String> menus) {
        JWTCreator.Builder builder = JWT.create();
        //角色
        if (!CollectionUtils.isEmpty(roles)) {
            builder.withClaim("role", roles);
        }
        //权限
        if (!CollectionUtils.isEmpty(authorities)) {
            builder.withClaim("authorities", authorities);
        }
        //菜单
        if (!CollectionUtils.isEmpty(authorities)) {
            builder.withClaim("menus", menus);
        }
        return builder.withClaim("username", username)
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 获取jwt的负载
     * @param token
     * @return
     */
    public Map<String, Claim> getClaim(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getClaims();
    }

    /**
     * 获取用户名
     * @param token
     * @return
     */
    public String getUsername(String token) {
        Claim claim = getClaim(token).get("username");
        if(claim == null) {
            return null;
        }
        return claim.asString();
    }


    /**
     * 获取租户
     * @param token
     * @return
     */
    public String getTenant(String token) {
        Claim claim = getClaim(token).get("tenant");
        if(claim == null) {
            return null;
        }
        return claim.asString();
    }

    /**
     * 获取用户类型
     * @param token
     * @return
     */
    public Integer getUserType(String token) {
        Claim claim = getClaim(token).get("type");
        if(claim == null) {
            return null;
        }
        return claim.asInt();
    }

    /**
     * 获取客户端唯一标识
     * @param token
     * @return
     */
    public String getUUID(String token) {
        Claim claim = getClaim(token).get("uuid");
        if(claim == null) {
            return null;
        }
        return claim.asString();
    }
}
