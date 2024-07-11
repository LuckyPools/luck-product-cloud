package com.luck.cloud.mybaits.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.luck.cloud.common.constant.Global;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * 默认字段填充
 * @author luck
 */
@Component
public class SysMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入数据时自动填充字段
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createDate = getFieldValByName("createDate", metaObject);
        Object createBy = getFieldValByName("createBy", metaObject);
        Object updateDate = getFieldValByName("updateDate", metaObject);
        Object updateBy = getFieldValByName("updateBy", metaObject);
        Object delFlag = getFieldValByName("delFlag", metaObject);
        if (null == createDate) {
            setFieldValByName("createDate", new Timestamp(System.currentTimeMillis()), metaObject);
        }
        if (null == createBy) {
            setFieldValByName("createBy", getCurUserId(metaObject), metaObject);
        }
        if (null == updateDate) {
            setFieldValByName("updateDate", new Timestamp(System.currentTimeMillis()), metaObject);
        }
        if (null == updateBy) {
            setFieldValByName("updateBy", getCurUserId(metaObject), metaObject);
        }
        if (null == delFlag) {
            setFieldValByName("isDelete", Global.NO, metaObject);
        }
    }

    /**
     * 更新数据时自动填充字段
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateDate", new Timestamp(System.currentTimeMillis()), metaObject);
        setFieldValByName("updateBy", getCurUserId(metaObject), metaObject);
    }

    /**
     * 获取当前登录用户id
     *
     * @param metaObject
     */
    private String getCurUserId(MetaObject metaObject) {
        return Global.YES;
    }
}
