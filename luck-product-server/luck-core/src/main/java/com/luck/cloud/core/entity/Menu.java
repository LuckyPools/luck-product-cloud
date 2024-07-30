package com.luck.cloud.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.sql.Timestamp;
import com.baomidou.mybatisplus.annotation.TableField;
import com.luck.cloud.base.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 系统菜单
 *
 * @author luck
 * @since 2024-07-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
public class Menu extends DataEntity<Menu>{

    /**
     * 菜单名称
     */
    @TableField
    private String name;

    /**
     * 权限
     */
    @TableField
    private String permission;

    /**
     * 父菜单ID
     */
    @TableField
    private String parentId;

    /**
     * 排序
     */
    @TableField
    private Integer sort;

    /**
     * 菜单类型
     */
    @TableField
    private String type;

    /**
     * 是否显示：D_SYS_YES_NO
     */
    @TableField
    private Boolean isShow;

    /**
     * 路由地址
     */
    @TableField
    private String path;

    /**
     * 菜单图标
     */
    @TableField
    private String icon;

    /**
     * 组件名称
     */
    @TableField
    private String component;

    /**
     * 组件地址
     */
    @TableField
    private String componentPath;


}
