package com.luck.cloud.core.dao;

import com.luck.cloud.core.entity.DictItem;
import com.luck.cloud.base.dao.BaseDao;
import org.springframework.stereotype.Component;

/**
 * 系统管理-字典项
 *
 * @author luck
 * @since 2024-10-17
 */
@Component(value = "dictItemDao")
public interface DictItemDao extends BaseDao<DictItem> {

}
