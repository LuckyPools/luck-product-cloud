package com.luck.cloud.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luck.cloud.base.dao.BaseDao;
import com.luck.cloud.base.param.SearchParam;
import com.luck.cloud.base.service.IBaseService;
import com.luck.cloud.base.vo.PageVO;
import com.luck.cloud.common.constant.Global;
import com.luck.cloud.common.exception.NotUniqueDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * 基础service实现类
 *
 * @author luck
 * @date 2023-10-26
 */
public class BaseServiceImpl<M extends BaseDao<T>, T> extends ServiceImpl<M, T> implements IBaseService<T> {

    @Autowired
    protected M baseDao;

    /**
     * 获取数据操作对象
     */
    public M getDao() {
        return baseDao;
    }

    /**
     * 按条件查询列表数据
     *
     * @param searchParam 查询条件
     * @return
     */
    @Override
    public List<T> queryList(SearchParam searchParam) {
        return this.getDao().queryList(searchParam);
    }

    /**
     * 分页查询列表数据
     *
     * @param pageVO 列表数据传输对象 {@link PageVO}
     * @return
     */
    @Override
    public PageVO<T> queryPage(PageVO<T> pageVO) {
        pageVO = Optional.ofNullable(pageVO).orElse(PageVO.emptyPageVO());
        pageVO.setIsPage(Global.YES);
        SearchParam searchParam = SearchParam.getSearchParam();
        searchParam.putAll(pageVO.getSearchParam());
        searchParam.setPage(pageVO);
        pageVO.setRecords(this.queryList(searchParam));
        return pageVO;
    }

    /**
     * 查询唯一数据
     *
     * @param searchParam 查询条件{@link SearchParam}
     * @return
     */
    @Override
    public T queryOne(SearchParam searchParam) {
        List<T> list = this.queryList(searchParam);
        if (list.size() > 1) {
            throw new NotUniqueDataException("数据不唯一");
        } else {
            return list.stream().findFirst().orElse(null);
        }
    }

    /**
     * 查询唯一数据
     *
     * @param id         id值
     * @return
     */
    @Override
    public T queryOneById(Serializable id) {
        Assert.notNull(id,"id不能为空");
        SearchParam searchParam = SearchParam.getSearchParam();
        searchParam.put("id",id);
        return this.queryOne(searchParam);
    }

    /**
     * 插入
     *
     * @param entity 实体
     * @return
     */
    @Override
    public boolean insert(T entity) {
        return super.save(entity);
    }

    /**
     * 批量插入
     *
     * @param entityList 实体列表
     * @return
     */
    @Override
    public boolean insertBatch(Collection<T> entityList) {
        return super.saveBatch(entityList);
    }

    /**
     * 更新
     *
     * @param entity 实体
     * @return
     */
    @Override
    public boolean updateById(T entity) {
        return this.updateById(entity);
    }

    /**
     * 批量更新
     *
     * @param entityList 实体列表
     * @return
     */
    @Override
    public boolean updateBatchById(Collection<T> entityList) {
        return super.updateBatchById(entityList);
    }

    /**
     * 保存或更新
     * @param entity
     * @return
     */
    @Override
    public boolean saveOrUpdate(T entity) {
        return super.saveOrUpdate(entity);
    }

    /**
     * 批量保存或更新
     * @param entityList
     * @return
     */
    @Override
    public boolean saveOrUpdateBatch(Collection<T> entityList) {
        return super.saveOrUpdateBatch(entityList);
    }

    /**
     * 根据id删除数据
     *
     * @param id 主键
     * @return
     */
    @Override
    public boolean deleteById(Serializable id) {
        return super.removeById(id);
    }

    /**
     * 根据id列表批量删除数据
     *
     * @param idList 主键列表
     * @return
     */
    @Override
    public boolean deleteByIds(Collection<? extends Serializable> idList) {
        return super.removeBatchByIds(idList);
    }

}
