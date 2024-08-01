package com.luck.cloud.core.service.impl;

import com.luck.cloud.core.entity.UserRole;
import com.luck.cloud.core.dao.UserRoleDao;
import com.luck.cloud.core.service.IUserRoleService;
import com.luck.cloud.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统用户角色
 *
 * @author luck
 * @since 2024-08-01
 */
@Service("coreUserRoleService")
@Transactional(rollbackFor = Exception.class)
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleDao, UserRole> implements IUserRoleService {
}
