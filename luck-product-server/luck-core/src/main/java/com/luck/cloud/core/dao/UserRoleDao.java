package com.luck.cloud.core.dao;

import com.luck.cloud.core.entity.UserRole;
import com.luck.cloud.base.dao.BaseDao;
import org.springframework.stereotype.Component;

/**
 * 系统用户角色
 *
 * @author luck
 * @since 2024-08-01
 */
@Component(value = "userRoleDao")
public interface UserRoleDao extends BaseDao<UserRole> {

}
