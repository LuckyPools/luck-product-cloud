package com.luck.cloud.core.vo;

import java.sql.Timestamp;
import com.luck.cloud.core.entity.LogRequest;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 系统请求日志
 *
 * @author luck
 * @since 2024-08-01
 */
@Data
@ApiModel("系统请求日志")
@AutoMapper(target = LogRequest.class)
public class LogRequestVO{

     @ApiModelProperty(value = "数据ID")
     private String id;

     @ApiModelProperty(value = "用户ID")
     private String userId;

     @ApiModelProperty(value = "用户名")
     private String username;

     @ApiModelProperty(value = "请求URL")
     private String url;

     @ApiModelProperty(value = "请求方法")
     private String method;

     @ApiModelProperty(value = "IP地址")
     private String ip;

     @ApiModelProperty(value = "请求参数")
     private String params;

     @ApiModelProperty(value = "代理信息")
     private String agent;


}
