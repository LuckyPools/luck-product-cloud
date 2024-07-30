package com.luck.cloud.core.vo;

import java.sql.Timestamp;
import com.luck.cloud.core.entity.Menu;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 系统菜单
 *
 * @author luck
 * @since 2024-07-30
 */
@Data
@ApiModel("系统菜单")
@AutoMapper(target = Menu.class)
public class MenuVO{

     @ApiModelProperty(value = "数据ID")
     private String id;

     @ApiModelProperty(value = "菜单名称")
     private String name;

     @ApiModelProperty(value = "权限")
     private String permission;

     @ApiModelProperty(value = "父菜单ID")
     private String parentId;

     @ApiModelProperty(value = "排序")
     private Integer sort;

     @ApiModelProperty(value = "菜单类型")
     private String type;

     @ApiModelProperty(value = "是否显示：D_SYS_YES_NO")
     private Boolean isShow;

     @ApiModelProperty(value = "路由地址")
     private String path;

     @ApiModelProperty(value = "菜单图标")
     private String icon;

     @ApiModelProperty(value = "组件名称")
     private String component;

     @ApiModelProperty(value = "组件地址")
     private String componentPath;


}
