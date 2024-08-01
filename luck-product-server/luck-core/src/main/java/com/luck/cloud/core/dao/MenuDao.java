package com.luck.cloud.core.dao;

import com.luck.cloud.base.dao.BaseDao;
import com.luck.cloud.core.entity.Menu;
import com.luck.cloud.core.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统目录
 *
 * @author luck
 * @since 2024-07-30
 */
@Component(value = "menuDao")
public interface MenuDao extends BaseDao<Menu> {

    /**
     * 根据角色ids获取菜单
     *
     * @param roleIds
     * @return
     */
    List<Menu> queryMenusByRoleIds(@Param("roleIds") List<String> roleIds);
}
