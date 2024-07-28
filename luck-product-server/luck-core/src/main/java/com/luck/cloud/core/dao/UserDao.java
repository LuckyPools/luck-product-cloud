package com.luck.cloud.core.dao;

import com.luck.cloud.base.dao.BaseDao;
import com.luck.cloud.core.entity.User;
import org.springframework.stereotype.Component;

/**
 * 用户信息Dao
 *
 * @author luck
 * @create 2024-4-10
 */
@Component("sysUserDao")
public interface UserDao extends BaseDao<User> {

}
