package com.luck.cloud.core.service;

import com.luck.cloud.core.entity.Role;
import com.luck.cloud.base.service.IBaseService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统角色
 *
 * @author luck
 * @since 2024-07-30
 */
public interface IRoleService extends IBaseService<Role> {

    /**
     * 根据用户id获取角色
     *
     * @param userId
     * @return
     */
    List<Role> queryRolesByUserId(String userId);
}
