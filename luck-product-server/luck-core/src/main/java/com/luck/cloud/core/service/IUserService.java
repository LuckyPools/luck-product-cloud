package com.luck.cloud.core.service;


import com.luck.cloud.base.service.IBaseService;
import com.luck.cloud.core.entity.User;

/**
 * 用户信息服务接口
 *
 * @author luck
 * @create 2024-04-10
 */
public interface IUserService extends IBaseService<User> {

    /**
     * 设置用户权限
     * @param user
     * @return
     */
    void setUserPermission(User user);
}
