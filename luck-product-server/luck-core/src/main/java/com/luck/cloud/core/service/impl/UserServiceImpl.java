package com.luck.cloud.core.service.impl;

import com.luck.cloud.base.entity.DataEntity;
import com.luck.cloud.base.param.SearchParam;
import com.luck.cloud.base.service.impl.BaseServiceImpl;
import com.luck.cloud.core.dao.UserDao;
import com.luck.cloud.core.entity.Menu;
import com.luck.cloud.core.entity.Role;
import com.luck.cloud.core.entity.User;
import com.luck.cloud.core.service.IMenuService;
import com.luck.cloud.core.service.IRoleMenuService;
import com.luck.cloud.core.service.IRoleService;
import com.luck.cloud.core.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户信息service实现类
 *
 * @author luck
 * @create 2024-04-10
 **/
@Service("sysUserService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements IUserService {


    @Resource
    private IRoleService roleService;

    @Resource
    private IMenuService menuService;

    /**
     * 设置用户权限
     * @param user
     * @return
     */
    @Override
    public void setUserPermission(User user) {
        if (user != null) {
            List<Role> roles = roleService.queryRolesByUserId(user.getId());
            user.setRoles(roles);
            if (!CollectionUtils.isEmpty(roles)) {
                List<String> roleIds = roles.stream().map(DataEntity::getId).collect(Collectors.toList());
                List<Menu> menus = menuService.queryMenusByRoleIds(roleIds);
                if (!CollectionUtils.isEmpty(menus)) {
                    List<String> permissions = menus.stream().map(Menu::getPermission)
                            .collect(Collectors.toList());
                    user.setPermissions(permissions);
                }
            }
        }
    }
}
