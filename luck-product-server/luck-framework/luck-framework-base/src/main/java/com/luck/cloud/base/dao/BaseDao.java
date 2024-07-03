package com.luck.cloud.base.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luck.cloud.base.param.SearchParam;

import java.util.List;

/**
 * 基础Dao层
 *
 * @author luck
 * @date 2023-10-26
 */
public interface BaseDao<T> extends BaseMapper<T> {
    /**
     * 查询列表数据
     *
     * @param searchParam 查询条件对象 {@link SearchParam}
     * @return
     */
    List<T> queryList(SearchParam searchParam);

}
