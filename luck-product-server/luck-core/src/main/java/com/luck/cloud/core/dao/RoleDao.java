package com.luck.cloud.core.dao;

import com.luck.cloud.core.entity.Role;
import com.luck.cloud.base.dao.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统角色
 *
 * @author luck
 * @since 2024-07-30
 */
@Component(value = "roleDao")
public interface RoleDao extends BaseDao<Role> {

    /**
     * 根据用户id获取角色
     *
     * @param userId
     * @return
     */
    List<Role> queryRolesByUserId(@Param("userId") String userId);
}
