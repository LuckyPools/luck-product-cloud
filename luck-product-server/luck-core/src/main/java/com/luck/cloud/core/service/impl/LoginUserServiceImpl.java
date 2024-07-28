package com.luck.cloud.core.service.impl;

import com.luck.cloud.base.param.SearchParam;
import com.luck.cloud.core.entity.LoginUser;
import com.luck.cloud.core.entity.User;
import com.luck.cloud.core.service.ILoginUserService;
import com.luck.cloud.core.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户信息service实现类
 *
 * @author luck
 * @create 2024-04-10
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginUserServiceImpl implements ILoginUserService {


    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) {
        SearchParam searchParam = SearchParam.getSearchParam();
        searchParam.put("loginName",s);
        User user = userService.queryOne(searchParam);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(user,loginUser);
        return loginUser;
    }
}
