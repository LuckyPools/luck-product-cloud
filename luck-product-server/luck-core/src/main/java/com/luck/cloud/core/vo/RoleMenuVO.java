package com.luck.cloud.core.vo;

import java.sql.Timestamp;
import com.luck.cloud.core.entity.RoleMenu;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 系统角色权限
 *
 * @author luck
 * @since 2024-07-30
 */
@Data
@ApiModel("系统角色权限")
@AutoMapper(target = RoleMenu.class)
public class RoleMenuVO{

     @ApiModelProperty(value = "数据ID")
     private String id;

     @ApiModelProperty(value = "角色ID")
     private String roleId;

     @ApiModelProperty(value = "菜单ID")
     private String menuId;


}
