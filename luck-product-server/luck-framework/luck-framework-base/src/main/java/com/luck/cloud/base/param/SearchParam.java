package com.luck.cloud.base.param;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;

/**
 * 查询参数
 *
 * @author luck
 * @date 2023-10-26
 */
public class SearchParam extends HashMap<String, Object> {


    public static SearchParam getSearchParam() {
        return new SearchParam();
    }

    /**
     * 分页对象
     */
    public IPage getPage() {
        return super.containsKey("searchPage") ? (IPage) MapUtils.getObject(this, "searchPage") : null;
    }

    /**
     * 将{@link IPage}设置为查询条件的一个项，触发mybatis-plus分页插件的分页功能
     *
     * @param page
     */
    public void setPage(IPage page) {
        put("searchPage", page);
    }
}
