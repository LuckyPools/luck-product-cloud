package com.luck.cloud.core.service.impl;

import com.luck.cloud.core.entity.Role;
import com.luck.cloud.core.dao.RoleDao;
import com.luck.cloud.core.service.IRoleService;
import com.luck.cloud.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统角色
 *
 * @author luck
 * @since 2024-07-30
 */
@Service("coreRoleService")
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends BaseServiceImpl<RoleDao, Role> implements IRoleService {

    /**
     * 根据用户id获取角色
     *
     * @param userId
     * @return
     */
    public List<Role> queryRolesByUserId(String userId){
        return getDao().queryRolesByUserId(userId);
    }
}