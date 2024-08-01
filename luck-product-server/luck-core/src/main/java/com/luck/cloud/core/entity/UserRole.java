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
 * 系统用户角色
 *
 * @author luck
 * @since 2024-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user_role")
public class UserRole extends DataEntity<UserRole>{

    /**
     * 用户ID
     */
    @TableField
    private String userId;

    /**
     * 角色ID
     */
    @TableField
    private String roleId;


}
