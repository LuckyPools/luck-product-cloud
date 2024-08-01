package com.luck.cloud.core.service.impl;

import com.luck.cloud.core.dao.MenuDao;
import com.luck.cloud.core.entity.Menu;
import com.luck.cloud.core.entity.Role;
import com.luck.cloud.core.service.IMenuService;
import com.luck.cloud.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统目录
 *
 * @author luck
 * @since 2024-07-30
 */
@Service("coreMenuService")
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl extends BaseServiceImpl<MenuDao, Menu> implements IMenuService {

    /**
     * 根据角色ids获取菜单
     *
     * @param roleIds
     * @return
     */
    public List<Menu> queryMenusByRoleIds(List<String> roleIds){
        return getDao().queryMenusByRoleIds(roleIds);
    }

}
