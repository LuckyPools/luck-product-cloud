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
 * 系统管理-字典类型
 *
 * @author luck
 * @since 2024-10-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict_type")
public class DictType extends DataEntity<DictType>{

    /**
     * 字典类型名称
     */
    @TableField
    private String name;

    /**
     * 字典类型编码
     */
    @TableField
    private String code;


}
