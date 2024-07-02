package com.luck.cloud.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luck.cloud.base.param.SearchParam;
import com.luck.cloud.base.vo.PageVO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 基础service
 *
 * @author luck
 * @date 2023-10-26
 */
public interface IBaseService<T> extends IService<T>{

    /**
     * 按条件查询列表数据
     *
     * @param searchParam 查询条件
     * @return
     */
    List<T> queryList(SearchParam searchParam);

    /**
     * 分页查询列表数据
     *
     * @param pageVO 列表数据传输对象 {@link PageVO}
     * @return
     */
    PageVO<T> queryPage(PageVO<T> pageVO);

    /**
     * 查询唯一数据
     *
     * @param searchParam 查询条件{@link SearchParam}
     * @return
     */
    T queryOne(SearchParam searchParam);

    /**
     * 查询唯一数据
     *
     * @param id         id值
     * @return
     */
    T queryOneById(Serializable id);

    /**
     * 插入
     *
     * @param entity 实体
     * @return
     */
    boolean insert(T entity);

    /**
     * 批量插入
     *
     * @param entityList 实体列表
     * @return
     */
    boolean insertBatch(Collection<T> entityList);

    /**
     * 更新
     *
     * @param entity 实体
     * @return
     */
    boolean updateById(T entity);

    /**
     * 批量更新
     *
     * @param entityList 实体列表
     * @return
     */
    boolean updateBatchById(Collection<T> entityList);

    /**
     * 批量保存更新
     *
     * @param entityList 实体列表
     * @return
     */
    boolean saveOrUpdateBatch(Collection<T> entityList,int batchSize);

    /**
     * 根据id删除数据
     *
     * @param id 主键
     * @return
     */
    boolean deleteById(Serializable id);

    /**
     * 根据id列表批量删除数据
     *
     * @param idList 主键列表
     * @return
     */
    boolean deleteByIds(Collection<? extends Serializable> idList);

}
