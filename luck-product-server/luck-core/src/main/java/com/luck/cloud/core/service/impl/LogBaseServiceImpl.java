package com.luck.cloud.core.service.impl;


import com.luck.cloud.common.constant.Global;
import com.luck.cloud.common.utils.RequestUtils;
import com.luck.cloud.core.dao.LogBaseDao;
import com.luck.cloud.core.entity.LogBase;
import com.luck.cloud.core.dto.LoginUser;
import com.luck.cloud.core.service.ILogBaseService;
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
@Service("coreLogBaseService")
@Transactional(rollbackFor = Exception.class)
public class LogBaseServiceImpl extends BaseServiceImpl<LogBaseDao, LogBase> implements ILogBaseService {

    /**
     * 写日志
     * @param request
     */
    @Override
    public void writeLog(HttpServletRequest request) {
        LogBase logBase = new LogBase();
        LoginUser user = new LoginUser();
        if (user != null && user.getId() != null) {
            logBase.setCreateBy(user.getId());
            logBase.setUpdateBy(user.getId());
            logBase.setUsername(user.getName());
            logBase.setUserId(user.getId());
        }
        logBase.setIp(RequestUtils.getIpAddr(request));
        logBase.setAgent(request.getHeader("user-agent"));
        logBase.setUrl(request.getRequestURL().toString());
        logBase.setMethod(request.getMethod());
        logBase.setParams(RequestUtils.getParams(request));
        logBase.setCreateDate(new Date());
        logBase.setUpdateDate(new Date());
        logBase.setIsDelete(Global.NO);
        save(logBase);
    }

    /**
     * 写日志
     * @param request
     */
    @Override
    public void writeLog(ServerHttpRequest request) {
        LogBase logBase = new LogBase();
        LoginUser user = new LoginUser();
        if (user != null && user.getId() != null) {
            logBase.setCreateBy(user.getId());
            logBase.setUpdateBy(user.getId());
            logBase.setUsername(user.getName());
            logBase.setUserId(user.getId());
        }
        logBase.setIp(RequestUtils.getIpAddr(request));
        logBase.setAgent(request.getHeaders().getFirst("user-agent"));
        logBase.setUrl(request.getURI().toString());
        logBase.setMethod(request.getMethod().toString());
        logBase.setParams(RequestUtils.getParams(request));
        logBase.setCreateDate(new Date());
        logBase.setUpdateDate(new Date());
        logBase.setIsDelete(Global.NO);
        save(logBase);
    }
}
