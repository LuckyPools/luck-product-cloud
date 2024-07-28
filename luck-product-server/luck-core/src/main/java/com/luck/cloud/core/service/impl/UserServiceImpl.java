package com.luck.cloud.core.service.impl;

import com.luck.cloud.base.service.impl.BaseServiceImpl;
import com.luck.cloud.core.dao.UserDao;
import com.luck.cloud.core.entity.User;
import com.luck.cloud.core.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户信息service实现类
 *
 * @author luck
 * @create 2024-04-10
 **/
@Service("sysUserService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements IUserService {

}
