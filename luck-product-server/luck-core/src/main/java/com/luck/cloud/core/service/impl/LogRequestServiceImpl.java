package com.luck.cloud.core.service.impl;


import com.luck.cloud.common.constant.Global;
import com.luck.cloud.common.utils.RequestUtils;
import com.luck.cloud.core.dao.LogRequestDao;
import com.luck.cloud.core.entity.LogRequest;
import com.luck.cloud.core.entity.LoginUser;
import com.luck.cloud.core.service.ILogRequestService;
import com.luck.cloud.base.service.impl.BaseServiceImpl;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 系统目录
 *
 * @author luck
 * @since 2024-07-30
 */
@Service("coreLogRequestService")
@Transactional(rollbackFor = Exception.class)
public class LogRequestServiceImpl extends BaseServiceImpl<LogRequestDao, LogRequest> implements ILogRequestService {

    /**
     * 写日志
     * @param request
     */
    @Override
    public void writeLog(HttpServletRequest request) {
        LogRequest logRequest = new LogRequest();
        LoginUser user = new LoginUser();
        if (user != null && user.getId() != null) {
            logRequest.setCreateBy(user.getId());
            logRequest.setUpdateBy(user.getId());
            logRequest.setUsername(user.getName());
            logRequest.setUserId(user.getId());
        }
        logRequest.setIp(RequestUtils.getIpAddr(request));
        logRequest.setAgent(request.getHeader("user-agent"));
        logRequest.setUrl(request.getRequestURL().toString());
        logRequest.setMethod(request.getMethod());
        logRequest.setParams(RequestUtils.getParams(request));
        logRequest.setCreateDate(new Date());
        logRequest.setUpdateDate(new Date());
        logRequest.setIsDelete(Global.NO);
        save(logRequest);
    }

    /**
     * 写日志
     * @param request
     */
    @Override
    public void writeLog(ServerHttpRequest request) {
        LogRequest logRequest = new LogRequest();
        LoginUser user = new LoginUser();
        if (user != null && user.getId() != null) {
            logRequest.setCreateBy(user.getId());
            logRequest.setUpdateBy(user.getId());
            logRequest.setUsername(user.getName());
            logRequest.setUserId(user.getId());
        }
        logRequest.setIp(RequestUtils.getIpAddr(request));
        logRequest.setAgent(request.getHeaders().getFirst("user-agent"));
        logRequest.setUrl(request.getURI().toString());
        logRequest.setMethod(request.getMethod().toString());
        logRequest.setParams(RequestUtils.getParams(request));
        logRequest.setCreateDate(new Date());
        logRequest.setUpdateDate(new Date());
        logRequest.setIsDelete(Global.NO);
        save(logRequest);
    }
}