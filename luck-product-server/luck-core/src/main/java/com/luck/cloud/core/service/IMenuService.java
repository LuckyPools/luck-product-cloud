package com.luck.cloud.core.service;

import com.luck.cloud.base.service.IBaseService;
import com.luck.cloud.core.entity.Menu;
import com.luck.cloud.core.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 系统目录
 *
 * @author luck
 * @since 2024-07-30
 */
public interface IMenuService extends IBaseService<Menu> {

    /**
     * 根据角色ids获取菜单
     *
     * @param roleIds
     * @return
     */
    List<Menu> queryMenusByRoleIds(List<String> roleIds);
}
