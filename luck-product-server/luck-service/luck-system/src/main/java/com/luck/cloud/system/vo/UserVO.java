package com.luck.cloud.system.vo;

import com.luck.cloud.system.entity.User;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serializable;


/**
 * 系统管理 - 用户信息VO
 *
 * @author luck
 * @create 2024-4-10
 **/
@Data
@AutoMapper(target = User.class)
public class UserVO implements Serializable {

    private String name;

    private String loginName;

    private String password;
}
