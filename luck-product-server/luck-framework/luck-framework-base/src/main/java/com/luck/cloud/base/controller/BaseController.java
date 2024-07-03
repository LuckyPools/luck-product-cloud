package com.luck.cloud.base.controller;

import com.luck.cloud.base.entity.BaseEntity;
import com.luck.cloud.base.service.IBaseService;
import com.luck.cloud.base.utils.ConvertUtils;
import com.luck.cloud.base.vo.PageVO;
import com.luck.cloud.base.vo.ResultVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.List;

public abstract class BaseController<T extends BaseEntity,V>{

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
    public abstract Class<T> getEntityClass();
    /**
     * 获取当前Controller数据库实体Entity
     *
     * @return
     */
    public abstract Class<V> getVOClass();


    /**
     * 分页模板
     *
     * @return
     */
    @GetMapping("/page")
    public PageVO<V> page(PageVO<T> pageVO) {
        getService().queryPage(pageVO);
        return ConvertUtils.convertPage(pageVO,getVOClass());
    }


    /**
     * 根据ID查询相关记录
     *
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public ResultVO<V> get(@PathVariable("id") String id) {
        T entity = getService().queryOneById(id);
        V result = ConvertUtils.convert(entity,getVOClass());
        return ResultVO.success(result);
    }

    /**
     * 插入
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @PostMapping("/save")
    public ResultVO save(@RequestBody V vo) {
        T entity = ConvertUtils.convert(vo,getEntityClass());
        getService().insert(entity);
        return ResultVO.success(entity.getId());
    }


    /**
     * 根据ID修改对饮记录
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/update")
    public ResultVO update(@Validated @RequestBody V vo) {
        T entity = ConvertUtils.convert(vo,getEntityClass());
        getService().updateById(entity);
        return ResultVO.success(entity.getId());
    }


    /**
     * 根据ID删除指定记录,这里被删除的记录会进入删除记录表
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public ResultVO delete(@PathVariable("id") Long id) {
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
    public ResultVO deleteBatch(@RequestBody List<Serializable> ids) {
        boolean deleteCount = getService().deleteByIds(ids);
        return ResultVO.success(deleteCount);
    }
}
