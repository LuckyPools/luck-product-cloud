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
 * 系统角色权限
 *
 * @author luck
 * @since 2024-07-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role_menu")
public class RoleMenu extends DataEntity<RoleMenu>{

    /**
     * 角色ID
     */
    @TableField
    private String roleId;

    /**
     * 菜单ID
     */
    @TableField
    private String menuId;


}
