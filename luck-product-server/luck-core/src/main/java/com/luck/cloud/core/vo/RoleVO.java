package com.luck.cloud.core.vo;

import java.sql.Timestamp;
import com.luck.cloud.core.entity.Role;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 系统角色
 *
 * @author luck
 * @since 2024-07-30
 */
@Data
@ApiModel("系统角色")
@AutoMapper(target = Role.class)
public class RoleVO{

     @ApiModelProperty(value = "数据ID")
     private String id;

     @ApiModelProperty(value = "角色名称")
     private String name;

     @ApiModelProperty(value = "角色编码")
     private String code;

     @ApiModelProperty(value = "角色类型")
     private String type;

     @ApiModelProperty(value = "排序")
     private Integer sort;


}
