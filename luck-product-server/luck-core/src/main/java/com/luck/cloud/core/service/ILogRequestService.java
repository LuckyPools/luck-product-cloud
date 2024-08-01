package com.luck.cloud.core.service;

import com.luck.cloud.core.entity.LogRequest;
import com.luck.cloud.base.service.IBaseService;
import org.springframework.http.server.reactive.ServerHttpRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统请求日志
 *
 * @author luck
 * @since 2024-08-01
 */
public interface ILogRequestService extends IBaseService<LogRequest> {

    /**
     * 写日志
     * @param request
     */
    public void writeLog(HttpServletRequest request);

    /**
     * 写日志
     * @param request
     */
    public void writeLog(ServerHttpRequest request);
}
