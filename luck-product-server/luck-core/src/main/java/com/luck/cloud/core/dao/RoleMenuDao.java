package com.luck.cloud.core.dao;

import com.luck.cloud.core.entity.RoleMenu;
import com.luck.cloud.base.dao.BaseDao;
import org.springframework.stereotype.Component;

/**
 * 系统角色权限
 *
 * @author luck
 * @since 2024-07-30
 */
@Component(value = "roleMenuDao")
public interface RoleMenuDao extends BaseDao<RoleMenu> {

}
