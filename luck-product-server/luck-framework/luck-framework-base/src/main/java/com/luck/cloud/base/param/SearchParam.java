package com.luck.cloud.base.param;

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
}
