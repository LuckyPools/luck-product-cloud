package com.luck.cloud.core.vo;

import com.luck.cloud.core.entity.User;
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

    private String id;

    private String isDelete;

    private String name;

    private String username;

    private String loginName;

    private String password;
}
