package com.luck.cloud.core.dao;

import com.luck.cloud.core.entity.DictType;
import com.luck.cloud.base.dao.BaseDao;
import org.springframework.stereotype.Component;

/**
 * 系统管理-字典类型
 *
 * @author luck
 * @since 2024-10-17
 */
@Component(value = "dictTypeDao")
public interface DictTypeDao extends BaseDao<DictType> {

}
