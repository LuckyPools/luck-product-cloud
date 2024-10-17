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
 * 系统管理-字典项
 *
 * @author luck
 * @since 2024-10-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict_item")
public class DictItem extends DataEntity<DictItem>{

    /**
     * 字典项名称
     */
    @TableField
    private String name;

    /**
     * 字典项编码
     */
    @TableField
    private String code;

    /**
     * 字典类型ID
     */
    @TableField
    private String dictId;

    /**
     * 字典类型编码
     */
    @TableField
    private String dictCode;

    /**
     * 父级ID
     */
    @TableField
    private String parentId;

    /**
     * 所有父级ID路径
     */
    @TableField
    private String parentIds;

    /**
     * 字典项名称路径
     */
    @TableField
    private String allNames;

    /**
     * 分类标识
     */
    @TableField
    private String filter;

    /**
     * 排序
     */
    @TableField
    private Integer sort;


}
