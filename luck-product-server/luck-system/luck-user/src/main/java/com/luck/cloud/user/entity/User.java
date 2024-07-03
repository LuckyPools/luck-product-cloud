package com.luck.cloud.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.luck.cloud.base.entity.BaseEntity;
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
public class User extends BaseEntity<User> {
    private static final long serialVersionUID = 1L;

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
     * <p>加密后存储</p>
     *
     */
    @TableField
    private String password;

}
