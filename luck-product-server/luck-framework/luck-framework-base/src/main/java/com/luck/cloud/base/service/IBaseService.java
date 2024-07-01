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
public interface IBaseService<T> extends IService<T> {
    /**
     * 查询列表数据
     * 默认进行数据范围控制
     *
     * @param tableVO 列表数据传输对象 {@link PageVO}
     * @return
     */
    PageVO<T> queryList(PageVO<T> tableVO);

    /**
     * 查询所有列表数据
     * 默认进行数据范围控制
     *
     * @return
     */
    default PageVO<T> queryList() {
        return queryList(PageVO.emptyPageVO());
    }

    /**
     * 按条件查询列表数据
     *
     * @param searchParam 查询条件
     * @return
     */
    List<T> queryList(SearchParam searchParam);



    /**
     * 分页查询列表数据
     * 默认进行数据范围控制
     *
     * @param tableVO 列表数据传输对象 {@link PageVO}
     * @return
     */
    PageVO<T> queryPage(PageVO<T> tableVO);


    /**
     * 查询唯一记录
     *
     * @param searchParam 查询条件{@link SearchParam}
     * @return
     */
    T queryOne(SearchParam searchParam);

    /**
     * 查询唯一记录
     *
     * @param id         id值
     * @param primaryKey 指定键名
     * @return
     */
    T queryOneById(String id, String... primaryKey);

    /**
     * 保存更新
     *
     * @param entity 实体
     * @return
     */
    boolean saveOrUpdate(T entity);

    /**
     * 批量保存更新
     *
     * @param entityList 实体列表
     * @return
     */
    boolean saveOrUpdateBatch(Collection<T> entityList);

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
    boolean update(T entity);

    /**
     * 批量更新
     *
     * @param entityList 实体列表
     * @return
     */
    boolean updateBatch(Collection<T> entityList);

    /**
     * 根据id删除记录
     *
     * @param id 主键
     * @return
     */
    boolean deleteById(Serializable id);

    /**
     * 根据id列表批量删除记录
     *
     * @param idList 主键列表
     * @return
     */
    boolean deleteByIds(Collection<? extends Serializable> idList);
}
