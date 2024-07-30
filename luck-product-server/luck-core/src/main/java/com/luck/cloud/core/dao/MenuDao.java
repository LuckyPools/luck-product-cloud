package com.luck.cloud.core.dao;

import com.luck.cloud.base.dao.BaseDao;
import com.luck.cloud.core.entity.Menu;
import org.springframework.stereotype.Component;

/**
 * 系统目录
 *
 * @author luck
 * @since 2024-07-30
 */
@Component(value = "menuDao")
public interface MenuDao extends BaseDao<Menu> {

}
