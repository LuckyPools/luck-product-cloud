package com.luck.cloud.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.luck.cloud.base.entity.DataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户实体类
 *
 * @author luck
 * @create 2024-4-10
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class User extends DataEntity<User> {

    /**
     * 用户名
     */
    @TableField
    private String name;

    /**
     * 登录账号
     */
    @TableField
    private String loginName;

    /**
     * 密码
     * 加密后存储
     */
    @TableField
    private String password;

}
