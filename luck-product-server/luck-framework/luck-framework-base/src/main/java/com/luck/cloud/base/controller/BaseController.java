package com.luck.cloud.base.controller;

import com.luck.cloud.base.entity.BaseEntity;
import com.luck.cloud.base.service.IBaseService;
import com.luck.cloud.base.utils.ConvertUtils;
import com.luck.cloud.base.vo.BaseVO;
import com.luck.cloud.base.vo.PageVO;
import com.luck.cloud.base.vo.ResultVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public abstract class BaseController<T extends BaseEntity,V extends BaseVO>{

    /**
     * 获取实际服务类
     *
     * @return
     */
    public abstract IBaseService<T> getService();

    /**
     * 获取当前Controller数据库实体Entity
     *
     * @return
     */
    public abstract T getEntity();

    /**
     * 获取当前Controller数据库实体Entity
     *
     * @return
     */
    public abstract V getVO();

    /**
     * 分页模板
     *
     * @return
     */
    @GetMapping("/pageList")
    public PageVO<V> pageList(PageVO<T> pageVO) {
        getService().queryPage(pageVO);
        return (PageVO<V>) ConvertUtils.convertPage(pageVO, getVO().getClass());
    }


    /**
     * 根据ID查询相关记录
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResultVO detail(@PathVariable("id") String id) {
        T result = getService().queryOneById(id);
        return ResultVO.success(ConvertUtils.convert(result,getVO().getClass()));
    }

    /**
     * 插入
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @PostMapping
    public ResultVO insert(@RequestBody V vo) {
        T entity = (T) ConvertUtils.convert(vo,getEntity().getClass());
        //插入
        getService().insert(entity);
        return ResultVO.success(entity.getId());
    }


    /**
     * 根据ID修改对饮记录
     *
     * @return
     * @throws Exception
     */
    @PutMapping
    public ResultVO update(@Validated @RequestBody V vo) {
        T entity = (T) ConvertUtils.convert(vo,getEntity().getClass());
        getService().update(entity);
        return ResultVO.success(entity.getId());
    }


    /**
     * 根据ID删除指定记录,这里被删除的记录会进入删除记录表
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultVO deleteById(@PathVariable("id") Long id) {
        getService().deleteById(id);
        return ResultVO.success(id);
    }

    /**
     * 删除批量ID对应的记录
     *
     * @param ids
     * @return
     */
    @PostMapping("/delete/batch")
    public ResultVO deleteBatchIds(@RequestBody List<Serializable> ids) {
        boolean deleteCount = getService().deleteByIds(ids);
        return ResultVO.success(deleteCount);
    }
}
