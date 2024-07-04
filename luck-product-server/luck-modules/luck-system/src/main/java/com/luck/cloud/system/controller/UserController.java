package com.luck.cloud.system.controller;

import com.luck.cloud.base.controller.BaseController;
import com.luck.cloud.base.service.IBaseService;
import com.luck.cloud.system.entity.User;
import com.luck.cloud.system.service.IUserService;
import com.luck.cloud.system.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息Controller
 *
 * @author luck
 * @create 2024-4-10
 **/
@RestController("sysUserController")
@RequestMapping("/sys/user")
public class UserController extends BaseController<User, UserVO> {

    @Autowired
    private IUserService userService;

    /**
     * 获取实际服务类
     *
     * @return
     */
    @Override
    public IBaseService<User> getService() {
        return this.userService;
    }

    /**
     * 获取当前Controller数据库实体Entity
     *
     * @return
     */
    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    /**
     * 获取当前Controller数据库实体Entity
     *
     * @return
     */
    @Override
    public Class<UserVO> getVOClass() {
        return UserVO.class;
    }
}
