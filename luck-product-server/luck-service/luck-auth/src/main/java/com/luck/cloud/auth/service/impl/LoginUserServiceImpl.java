package com.luck.cloud.auth.service.impl;

import com.luck.cloud.auth.dao.LoginUserDao;
import com.luck.cloud.auth.entity.LoginUser;
import com.luck.cloud.auth.service.ILoginUserService;
import com.luck.cloud.base.param.SearchParam;
import com.luck.cloud.base.service.impl.BaseServiceImpl;
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
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginUserServiceImpl extends BaseServiceImpl<LoginUserDao, LoginUser> implements ILoginUserService {

    @Override
    public UserDetails loadUserByUsername(String s) {
        SearchParam searchParam = SearchParam.getSearchParam();
        searchParam.put("loginName",s);
        LoginUser loginUser = this.queryOne(searchParam);
        return loginUser;
    }
}
