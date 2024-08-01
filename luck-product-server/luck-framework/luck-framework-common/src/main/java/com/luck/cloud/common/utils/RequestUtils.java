package com.luck.cloud.common.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Objects;

/**
 * 处理请求工具类
 *
 * @author ChangjieLai
 * @create 2023-06-14 20:07
 **/
public class RequestUtils {

    public static final String UNKNOWN = "unknown";

    public static final String X_FORWARDED_FOR = "x-forwarded-for";

    public static final String PROXY_CLIENT_IP = "Proxy-Client-IP";

    public static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

    public static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";

    public static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";

    public static final String X_REAL_IP = "X-Real-IP";

    public static final String LOCAL_IP_V4 = "127.0.0.1";

    public static final String LOCAL_IP_V6 = "0:0:0:0:0:0:0:1";

    public static final String CDN_SRC_IP = "cdn-src-ip";

    /**
     * 获取用户远程地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return UNKNOWN;
        }
        String ip = request.getHeader(X_FORWARDED_FOR);
        if (StringUtils.isBlank(ip)|| UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(PROXY_CLIENT_IP);
        }
        if (StringUtils.isBlank(ip)|| UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(WL_PROXY_CLIENT_IP);
        }
        if (StringUtils.isBlank(ip)|| UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HTTP_CLIENT_IP);
        }
        if (StringUtils.isBlank(ip)|| UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HTTP_X_FORWARDED_FOR);
        }
        if (StringUtils.isBlank(ip)|| UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(X_REAL_IP);
        }
        if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return LOCAL_IP_V6.equals(ip) ? LOCAL_IP_V4 : ip.split(",")[0];
    }

    /**
     * 获取用户远程地址
     */
    public static String getIpAddr(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        String ip = headers.getFirst(X_FORWARDED_FOR);
        if (ip != null && ip.length() != 0 && !UNKNOWN.equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.contains(",")) {
                ip = ip.split(",")[0];
            }
        }
        if (StringUtils.isBlank(ip)|| UNKNOWN.equalsIgnoreCase(ip)) {
            ip = headers.getFirst(PROXY_CLIENT_IP);
        }
        if (StringUtils.isBlank(ip)|| UNKNOWN.equalsIgnoreCase(ip)) {
            ip = headers.getFirst(WL_PROXY_CLIENT_IP);
        }
        if (StringUtils.isBlank(ip)|| UNKNOWN.equalsIgnoreCase(ip)) {
            ip = headers.getFirst(HTTP_CLIENT_IP);
        }
        if (StringUtils.isBlank(ip)|| UNKNOWN.equalsIgnoreCase(ip)) {
            ip = headers.getFirst(HTTP_X_FORWARDED_FOR);
        }
        if (StringUtils.isBlank(ip)|| UNKNOWN.equalsIgnoreCase(ip)) {
            ip = headers.getFirst(X_REAL_IP);
        }
        if (StringUtils.isBlank(ip)|| UNKNOWN.equalsIgnoreCase(ip)) {
            ip = Objects.requireNonNull(request.getRemoteAddress()).getAddress().getHostAddress();
            if (ip.equals(LOCAL_IP_V4) || ip.equals(LOCAL_IP_V6)) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                    ip = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
        return ip;
    }

    /**
     * 获取请求参数
     * @param request
     * @return
     */
    public static String getParams(HttpServletRequest request){
        StringBuilder params = new StringBuilder();
        int index = 0;
        for (Object param : request.getParameterMap().keySet()) {
            params.append((index++ == 0 ? "" : "&") + param + "=");
            params.append( request.getParameter((String) param));
        }
        return params.toString();
    }


    /**
     * 获取请求参数
     * @param request
     * @return
     */
    public static String getParams(ServerHttpRequest request){
        Map paramsMap = request.getQueryParams();
        return JSON.toJSONString(paramsMap);
    }
}
