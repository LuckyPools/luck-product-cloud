package com.luck.cloud.core.vo;

import java.sql.Timestamp;
import com.luck.cloud.core.entity.DictItem;
import io.github.linpeilie.annotations.AutoMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 系统管理-字典项
 *
 * @author luck
 * @since 2024-10-17
 */
@Data
@ApiModel("系统管理-字典项")
@AutoMapper(target = DictItem.class)
public class DictItemVO{

     @ApiModelProperty(value = "数据ID")
     private String id;

     @ApiModelProperty(value = "字典项名称")
     private String name;

     @ApiModelProperty(value = "字典项编码")
     private String code;

     @ApiModelProperty(value = "字典类型ID")
     private String dictId;

     @ApiModelProperty(value = "字典类型编码")
     private String dictCode;

     @ApiModelProperty(value = "父级ID")
     private String parentId;

     @ApiModelProperty(value = "所有父级ID路径")
     private String parentIds;

     @ApiModelProperty(value = "分类标识")
     private String filter;

     @ApiModelProperty(value = "排序")
     private Integer sort;


}
