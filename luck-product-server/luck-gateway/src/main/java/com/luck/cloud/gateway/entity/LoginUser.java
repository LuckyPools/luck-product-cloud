package com.luck.cloud.gateway.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.luck.cloud.base.entity.DataEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 用户实体类
 *
 * @author luck
 * @create 2024-4-10
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class LoginUser extends DataEntity<LoginUser> implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.loginName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
