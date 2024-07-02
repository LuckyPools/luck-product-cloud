package com.luck.cloud.base.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.luck.cloud.base.param.SearchParam;
import com.luck.cloud.common.constant.Global;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 分页数据
 *
 * @author luck
 * @date 2023-10-26
 */
@Data
public class PageVO<T> extends ResultVO implements IPage<T>, Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 记录总数
     */
    private Integer total = 0;

    /**
     * 搜索条件
     */
    private SearchParam searchParam = SearchParam.getSearchParam();

    /**
     * 返回列表
     */
    private List<T> records;

    /**
     * 排序 条件
     */
    Map<String, String> sorts;

    /**
     * 是否分页
     */
    private String page;

    /**
     * 当前页码
     */
    private Integer pageNo = -1;

    /**
     * 页码大小
     */
    private Integer pageSize = 10;

    /**
     * 获取不分页查询类型
     *
     * @param <T>
     * @return
     */
    public static <T> PageVO<T> emptyListVO() {
        PageVO<T> tableVO = new PageVO<>();
        tableVO.setPage(Global.NO);
        return tableVO;
    }

    /**
     * 获取分页查询类型
     *
     * @param <T>
     * @return
     */
    public static <T> PageVO<T> emptyPageVO() {
        PageVO<T> tableVO = new PageVO<>();
        tableVO.setPage(Global.YES);
        tableVO.setPageNo(1);
        return tableVO;
    }

    /**
     * 是否分页
     *
     * @return
     */
    public boolean isPage() {
        return Global.YES.equals(page);
    }


    /**
     * 设置是否分页
     *
     * @param page
     */
    public void setPage(String page) {
        this.page = page;
        // 不分页，则pageSize必须为小于0
        if (Global.NO.equals(page)) {
            this.pageSize = -1;
        }
    }

    /**
     * 原isPage名称修改为page后，本方法过期
     *
     * @param page
     */
    public void setIsPage(String page) {
        this.page = page;
        // 不分页，则pageSize必须为小于0
        if (Global.NO.equals(page)) {
            this.pageSize = -1;
        }
    }

    /**
     * 分页记录列表
     *
     * @return 分页对象记录列表
     */
    @Override
    public List<T> getRecords() {
        return this.records;
    }

    /**
     * 设置分页记录列表
     *
     * @param records
     */
    @Override
    public IPage<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    /**
     * 当前满足条件总行数
     *
     * @return 总条数
     */
    @Override
    public long getTotal() {
        return this.total;
    }

    /**
     * 设置当前满足条件总行数
     *
     * @param total
     */
    @Override
    public IPage<T> setTotal(long total) {
        this.total = (int) total;
        return this;
    }

    /**
     * 当前分页总页数
     *
     * @return 总页数
     */
    @Override
    public long getSize() {
        return this.pageSize;
    }

    /**
     * 设置当前分页总页数
     *
     * @param size
     */
    @Override
    public IPage<T> setSize(long size) {
        this.pageSize = (int) size;
        return this;
    }

    /**
     * 当前页，默认 1
     *
     * @return 当然页
     */
    @Override
    public long getCurrent() {
        return this.pageNo;
    }

    /**
     * 设置当前页
     *
     * @param current
     */
    @Override
    public IPage<T> setCurrent(long current) {
        this.pageNo = (int) current;
        return this;
    }

    @Override
    public List<OrderItem> orders() {
        return null;
    }
}
