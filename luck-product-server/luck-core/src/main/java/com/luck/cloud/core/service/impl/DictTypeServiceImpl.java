package com.luck.cloud.core.service.impl;

import com.luck.cloud.core.entity.DictType;
import com.luck.cloud.core.dao.DictTypeDao;
import com.luck.cloud.core.service.IDictTypeService;
import com.luck.cloud.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统管理-字典类型
 *
 * @author luck
 * @since 2024-10-17
 */
@Service("coreDictTypeService")
@Transactional(rollbackFor = Exception.class)
public class DictTypeServiceImpl extends BaseServiceImpl<DictTypeDao, DictType> implements IDictTypeService {
}
