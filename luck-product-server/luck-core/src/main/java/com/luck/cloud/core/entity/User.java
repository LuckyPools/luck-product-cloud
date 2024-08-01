package com.luck.cloud.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.luck.cloud.base.entity.DataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;

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
     * 姓名
     */
    @TableField
    private String name;

    /**
     * 用户名
     */
    @TableField
    private String username;

    /**
     * 密码
     *
     */
    @TableField
    private String password;

    /**
     * 角色
     */
    @TableField(exist = false)
    private List<Role> roles;


    /**
     * 权限
     */
    @TableField(exist = false)
    private List<String> permissions;
}
