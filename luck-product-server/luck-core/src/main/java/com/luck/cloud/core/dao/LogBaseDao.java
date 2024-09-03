package com.luck.cloud.core.dao;

import com.luck.cloud.core.entity.LogBase;
import com.luck.cloud.base.dao.BaseDao;
import org.springframework.stereotype.Component;

/**
 * 系统请求日志
 *
 * @author luck
 * @since 2024-08-01
 */
@Component(value = "logBaseDao")
public interface LogBaseDao extends BaseDao<LogBase> {

}
