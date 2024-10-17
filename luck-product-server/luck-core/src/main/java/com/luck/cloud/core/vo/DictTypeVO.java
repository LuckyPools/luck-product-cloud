package com.luck.cloud.core.vo;

import java.sql.Timestamp;
import com.luck.cloud.core.entity.DictType;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 系统管理-字典类型
 *
 * @author luck
 * @since 2024-10-17
 */
@Data
@ApiModel("系统管理-字典类型")
@AutoMapper(target = DictType.class)
public class DictTypeVO{

     @ApiModelProperty(value = "数据ID")
     private String id;

     @ApiModelProperty(value = "字典类型名称")
     private String name;

     @ApiModelProperty(value = "字典类型编码")
     private String code;


}
