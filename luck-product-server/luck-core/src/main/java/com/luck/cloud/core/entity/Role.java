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
 * 系统角色
 *
 * @author luck
 * @since 2024-07-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class Role extends DataEntity<Role>{

    /**
     * 角色名称
     */
    @TableField
    private String name;

    /**
     * 角色编码
     */
    @TableField
    private String code;

    /**
     * 角色类型
     */
    @TableField
    private String type;

    /**
     * 排序
     */
    @TableField
    private Integer sort;


}
