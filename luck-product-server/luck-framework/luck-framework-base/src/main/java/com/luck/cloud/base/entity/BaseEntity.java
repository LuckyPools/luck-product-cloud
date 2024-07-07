package com.luck.cloud.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 基础实体类
 * 通过Model类获得mybatisPlus封装的基于当前表（泛型）的基础方法
 * @author luck
 * @date 2023-10-26
 */
@Data
public abstract class BaseEntity<T extends BaseEntity<T>> extends Model<T> {

    private static final long serialVersionUID = 1L;

    @TableId
    protected String id;
}


