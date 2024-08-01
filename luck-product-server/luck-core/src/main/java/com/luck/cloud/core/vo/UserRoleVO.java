package com.luck.cloud.core.vo;

import java.sql.Timestamp;
import com.luck.cloud.core.entity.UserRole;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 系统用户角色
 *
 * @author luck
 * @since 2024-08-01
 */
@Data
@ApiModel("系统用户角色")
@AutoMapper(target = UserRole.class)
public class UserRoleVO{

     @ApiModelProperty(value = "数据ID")
     private String id;

     @ApiModelProperty(value = "用户ID")
     private String userId;

     @ApiModelProperty(value = "角色ID")
     private String roleId;


}
