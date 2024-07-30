package com.luck.cloud.core.dao;

import com.luck.cloud.core.entity.Role;
import com.luck.cloud.base.dao.BaseDao;
import org.springframework.stereotype.Component;

/**
 * 系统角色
 *
 * @author luck
 * @since 2024-07-30
 */
@Component(value = "roleDao")
public interface RoleDao extends BaseDao<Role> {

}
