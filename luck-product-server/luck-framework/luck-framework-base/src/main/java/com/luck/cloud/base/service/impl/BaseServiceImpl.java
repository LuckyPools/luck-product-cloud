package com.luck.cloud.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luck.cloud.base.dao.BaseDao;
import com.luck.cloud.base.param.SearchParam;
import com.luck.cloud.base.service.IBaseService;
import com.luck.cloud.base.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 基础service实现类
 *
 * @author luck
 * @date 2023-10-26
 */
public class BaseServiceImpl<M extends BaseDao<T>, T> implements IBaseService<T> {

    @Autowired
    protected M baseDao;

    public M getDao() {
        return baseDao;
    }


    /**
     * 查询列表数据
     * 默认进行数据范围控制
     *
     * @param tableVO 列表数据传输对象 {@link PageVO}
     * @return
     */
    @Override
    public PageVO<T> queryList(PageVO<T> tableVO) {
        return null;
    }

    /**
     * 按条件查询列表数据
     *
     * @param searchParam 查询条件
     * @return
     */
    @Override
    public List<T> queryList(SearchParam searchParam) {
        return null;
    }

    /**
     * 分页查询列表数据
     * 默认进行数据范围控制
     *
     * @param tableVO 列表数据传输对象 {@link PageVO}
     * @return
     */
    @Override
    public PageVO<T> queryPage(PageVO<T> tableVO) {
        return null;
    }

    /**
     * 查询唯一记录
     *
     * @param searchParam 查询条件{@link SearchParam}
     * @return
     */
    @Override
    public T queryOne(SearchParam searchParam) {
        return null;
    }

    /**
     * 查询唯一记录
     *
     * @param id         id值
     * @param primaryKey 指定键名
     * @return
     */
    @Override
    public T queryOneById(String id, String... primaryKey) {
        return null;
    }

    /**
     * 保存更新
     *
     * @param entity 实体
     * @return
     */
    @Override
    public boolean saveOrUpdate(T entity) {
        return false;
    }

    @Override
    public T getOne(Wrapper<T> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<T> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<T> getBaseMapper() {
        return null;
    }

    @Override
    public Class<T> getEntityClass() {
        return null;
    }

    @Override
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        return false;
    }

    /**
     * 批量保存更新
     *
     * @param entityList 实体列表
     * @return
     */
    @Override
    public boolean saveOrUpdateBatch(Collection<T> entityList) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<T> entityList, int batchSize) {
        return false;
    }

    /**
     * 插入
     *
     * @param entity 实体
     * @return
     */
    @Override
    public boolean insert(T entity) {
        return false;
    }

    /**
     * 批量插入
     *
     * @param entityList 实体列表
     * @return
     */
    @Override
    public boolean insertBatch(Collection<T> entityList) {
        return false;
    }

    /**
     * 更新
     *
     * @param entity 实体
     * @return
     */
    @Override
    public boolean update(T entity) {
        return false;
    }

    /**
     * 批量更新
     *
     * @param entityList 实体列表
     * @return
     */
    @Override
    public boolean updateBatch(Collection<T> entityList) {
        return false;
    }

    /**
     * 根据id删除记录
     *
     * @param id 主键
     * @return
     */
    @Override
    public boolean deleteById(Serializable id) {
        return false;
    }

    /**
     * 根据id列表批量删除记录
     *
     * @param idList 主键列表
     * @return
     */
    @Override
    public boolean deleteByIds(Collection<? extends Serializable> idList) {
        return false;
    }
}
