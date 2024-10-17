package com.luck.cloud.core.service;

import com.luck.cloud.core.entity.DictItem;
import com.luck.cloud.base.service.IBaseService;

import java.util.List;

/**
 * 系统管理-字典项
 *
 * @author luck
 * @since 2024-10-17
 */
public interface IDictItemService extends IBaseService<DictItem> {

    /**
     * 根据字典编码获取字典列表
     * @param dictCode
     * @return
     */
    public List<DictItem> queryListByDictCode(String dictCode);
}
