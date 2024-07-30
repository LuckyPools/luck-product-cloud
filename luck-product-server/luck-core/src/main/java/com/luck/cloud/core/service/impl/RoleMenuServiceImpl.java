package com.luck.cloud.core.service.impl;

import com.luck.cloud.core.entity.RoleMenu;
import com.luck.cloud.core.dao.RoleMenuDao;
import com.luck.cloud.core.service.IRoleMenuService;
import com.luck.cloud.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统角色权限
 *
 * @author luck
 * @since 2024-07-30
 */
@Service("coreRoleMenuService")
@Transactional(rollbackFor = Exception.class)
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenuDao, RoleMenu> implements IRoleMenuService {
}
