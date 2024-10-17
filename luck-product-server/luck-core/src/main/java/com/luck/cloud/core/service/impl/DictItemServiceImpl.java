package com.luck.cloud.core.service.impl;

import com.luck.cloud.core.entity.DictItem;
import com.luck.cloud.core.dao.DictItemDao;
import com.luck.cloud.core.service.IDictItemService;
import com.luck.cloud.base.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统管理-字典项
 *
 * @author luck
 * @since 2024-10-17
 */
@Service("coreDictItemService")
@Transactional(rollbackFor = Exception.class)
public class DictItemServiceImpl extends BaseServiceImpl<DictItemDao, DictItem> implements IDictItemService {
}
