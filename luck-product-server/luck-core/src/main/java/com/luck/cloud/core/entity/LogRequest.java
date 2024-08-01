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
 * 系统请求日志
 *
 * @author luck
 * @since 2024-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_log_request")
public class LogRequest extends DataEntity<LogRequest>{

    /**
     * 用户ID
     */
    @TableField
    private String userId;

    /**
     * 用户名
     */
    @TableField
    private String username;

    /**
     * 请求URL
     */
    @TableField
    private String url;

    /**
     * 请求方法
     */
    @TableField
    private String method;

    /**
     * IP地址
     */
    @TableField
    private String ip;

    /**
     * 请求参数
     */
    @TableField
    private String params;

    /**
     * 代理信息
     */
    @TableField
    private String agent;


}
