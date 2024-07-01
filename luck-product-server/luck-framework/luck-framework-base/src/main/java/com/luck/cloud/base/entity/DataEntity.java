package com.luck.cloud.base.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 数据实体类
 *
 * @author luck
 * @date 2023-10-26
 */
@Data
public abstract class DataEntity<T extends DataEntity<T>> extends BaseEntity<T> {

    /**
     * 创建人ID
     */
    @TableField(fill = FieldFill.INSERT)
    String createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    Date createDate;

    /**
     * 更新人ID
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    String updateBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    Timestamp updateDate;

    /**
     * 备注
     */
    @TableField
    String remarks;

    /**
     * 删除标记 0：未删除 1：已删除
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    String isDelete;

}
