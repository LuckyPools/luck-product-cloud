package com.luck.cloud.auth.service.impl;

import com.luck.cloud.auth.dao.UserDao;
import com.luck.cloud.auth.entity.User;
import com.luck.cloud.auth.service.IUserService;
import com.luck.cloud.base.param.SearchParam;
import com.luck.cloud.base.service.impl.BaseServiceImpl;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

/**
 * 用户信息service实现类
 *
 * @author luck
 * @create 2024-04-10
 **/
@Service("sysUserService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements IUserService{

    @Override
    public Mono<UserDetails> findByUsername(String s) {
        SearchParam searchParam = SearchParam.getSearchParam();
        searchParam.put("loginName",s);
        User user = this.queryOne(searchParam);
        if(user != null){

        }
        return null;
    }
}
