<template>
  <div>
      <a-table
          class="al-table"
          :tableLayout="tableLayout"
          :bordered="bordered"
          :childrenColumnName="childrenColumnName"
          :components="components"
          :defaultExpandAllRows="defaultExpandAllRows"
          :defaultExpandedRowKeys="defaultExpandedRowKeys"
          :expandedRowKeys="expandedRowKeys"
          :expandedRowRender="expandedRowRender"
          :expandRowByClick="expandRowByClick"
          :expandIcon="expandIcon"
          :expandIconColumnIndex="expandIconColumnIndex"
          :footer="footer"
          :indentSize="indentSize"
          :locale="locale"
          :columns="columns"
          :size="size"
          :pagination="pagination"
          :rowClassName="rowClassName"
          :rowKey="rowKey"
          :rowSelection="rowSelection"
          :scroll="scroll"
          :showHeader="showHeader"
          :title="title"
          :customHeaderRow="customHeaderRow"
          :customRow="customRow"
          :getPopupContainer="getPopupContainer"
          :transformCellText="transformCellText"
          :dataSource="tableData"
          :loading="tableLoading"
      >
          <template  v-for="column in columns"  :slot="column.scopedSlots?column.scopedSlots.customRender:''" slot-scope="text,record" >
              <slot :name="column.scopedSlots?column.scopedSlots.customRender:''" v-bind:scope="record" ></slot>
          </template>
      </a-table>
  </div>
</template>

<script>
import props from "@/views/system/user/props";
import {eachTreeData, getFieldValue, getOrderItems, reloadData} from "@/views/system/user/utils";

export default {
    name: 'AproTable',
    data() {
        return {
          tableData: [],
          tableLoading: false,
          tablePage: '',
          tableLimit: '',
          tableSorter: '',
          tableFilters: '',
          tableTotal: '',
          tableWhere: ''
        };
    },
    props: {
      ...props
    },
    computed: {

    },
    created() {

    },
    mounted() {

    },
    methods: {
        reload(option, parent, resolve) {
            // 自定义请求方法
            // 直接指定数据
            if (Array.isArray(this.datasource)) {
                this.tableLoading = true;
                const { page, data, total } = reloadData(
                    this.datasource,
                    this.tableSorter ?? this.defaultSorter,
                    this.tablePage,
                    this.tableLimit,
                    this.needPage
                );
                this.tablePage = page;
                this.tableData = data;
                this.tableTotal = total;
                this.tableLoading = false;
                const result = { data, total, response: this.datasource };
                this.onRenderDone(result, page, total);
                return;
            }

            if (this.api.query) {
                if (typeof this.api.query === 'function') {
                    this.tableLoading = true;
                    // 排序参数
                    // todo 修正请求参数
                    const defaultSorter = this.getDefaultSorter();
                    const defaultFilters = this.getDefaultFilters();
                    const reqParams = {
                        page: this.tablePage,
                        limit: this.tableLimit,
                        where: {...this.tableWhere},
                        // 排序请求参数
                        order: this.getRequestOrders(defaultSorter),
                        // 筛选请求参数
                        filterValue: this.getRequestFilters(defaultFilters),
                        // 原始排序方式
                        sorter: this.tableSorter ?? defaultSorter,
                        // 原始筛选条件
                        filters: this.tableFilters ?? defaultFilters,
                        // 树形懒加载时父级数据
                        parent
                    };
                    const req = this.getData(reqParams);
                    Promise.resolve(req)
                        .then((result) => {
                            if (typeof result !== 'undefined') {
                                const { data, count } = this.getResponseResult(result);
                                this.datasourceCallback(data, count, parent, resolve, result);
                            }
                        })
                        .catch((e) => {
                            this.datasourceCallback(e?.message);
                            if (resolve) {
                                console.error(e);
                            }
                        });
                    return;
                }
            }
        },

        /* funtion 数据源请求完后回调 */
        datasourceCallback(data, total, parent, resolve, response) {
            if (Array.isArray(data)) {
                if (resolve) {
                    resolve(data);
                } else {
                    // 自动修正页码
                    if (
                        this.autoAmendPage &&
                        this.needPage &&
                        !data.length &&
                        total &&
                        this.tablePage &&
                        this.tableLimit
                    ) {
                        const maxPage = Math.ceil(total / this.tableLimit);
                        if (maxPage && this.tablePage > maxPage) {
                            this.tablePage = maxPage;
                            this.reload();
                            return;
                        }
                    }
                    // 获取返回的数据
                    this.tableData = data;
                    this.tableTotal = total || data.length;
                }
                this.tableLoading = false;
                const result = {
                    data: this.tableData,
                    total: this.tableTotal,
                    response
                };
                this.onRenderDone(result, this.tablePage, this.tableTotal, parent);
            } else if (typeof data === 'string') {
                this.tableLoading = false;
                this.errorText = data;
            } else {
                this.tableLoading = false;
                this.errorText = '获取数据失败';
                console.error('datasource返回的数据错误: ', data);
            }
        },

        /* 表格数据渲染完成 */
        onRenderDone(result, page, total, parent) {
            this.$emit('done', result, page, total, parent);
        },

        async getData(page, limit, where, order, filterValue, sorter, filters, parent) {
            if (this.api.query) {
                if (typeof this.api.query === 'string') {
                    return this.api.query;
                } else {
                    let searchData = {
                        searchParam: {}
                    };
                    searchData.pageNo = page;
                    searchData.pageSize = limit;
                    // 通常排序对象只有一个
                    if (order) {
                        Object.keys(order).forEach((key) => {
                            if (order[key] != null && order[key] !== undefined) {
                                searchData.searchParam[key] = order[key];
                            } else {
                                searchData.searchParam[key] = '';
                            }
                        });
                    }
                    if (where) {
                        Object.keys(where).forEach((key) => {
                            if (where[key] != null && where[key] !== undefined) {
                                searchData.searchParam[key] = where[key];
                            } else {
                                searchData.searchParam[key] = '';
                            }
                        });
                    }
                    const res = await this.api.query(searchData);
                    if (res.records) {
                        return {
                            list: res.records,
                            count: res.total
                        };
                    } else {
                        return res;
                    }
                }
            }
        },

        /* 获取请求的数据和总数量 */
        getResponseResult(data) {
            const result = this.parseData ? this.parseData(data) : data;
            if (!result || Array.isArray(result)) {
                return {
                    code: 0,
                    msg: '',
                    data: this.getResponseData(result),
                    success: true,
                    successCode: 0
                };
            }
            const { dataName, countName, statusCode, statusName, msgName } = {
                ...this.response
            };
            if (!dataName) {
                return {};
            }
            const code = getFieldValue(result, statusName);
            return {
                data: this.getResponseData(getFieldValue(result, dataName)),
                count: countName ? getFieldValue(result, countName) : 0,
                msg: getFieldValue(result, msgName),
                success: statusCode === code,
                successCode: statusCode,
                code
            };
        },


        /* 树形懒加载时给数据增加可展开的字段标识 */
        getResponseData(data) {
            if (!data || !this.lazy) {
                return data;
            }
            const name = this.treeProps?.hasChildren ?? 'hasChildren';
            return data.map((d) => {
                const temp = { ...d };
                temp[name] = temp[name] ?? true;
                return temp;
            });
        },

        /* 获取默认排序方式 */
        getDefaultSorter() {
            return this.defaultSort;
        },

        /* 获取默认的筛选条件 */
        getDefaultFilters() {
            const filters = {};
            eachTreeData(this.columns, (d) => {
                if (
                    typeof d.columnKey === 'string' &&
                    d.columnKey.length &&
                    d.filteredValue
                ) {
                    filters[d.columnKey] = d.filteredValue;
                }
            });
            return filters;
        },

        /* 生成请求的排序参数 */
        getRequestOrders(defaultSorter) {
            const orders = {};
            const { sortName, orderName } = {
                ...this.request
            };
            if (!sortName || !orderName) {
                return orders;
            }
            const ascValue =
                this.ascendValue;
            const descValue =
                this.descendValue;
            const items = getOrderItems(this.tableSorter ?? defaultSorter);
            if (items.length === 1) {
                orders[sortName] = items[0].field;
                orders[orderName] = items[0].descend ? descValue : ascValue;
            } else if (items.length) {
                orders[sortName] = items
                    .map((d) => [d.field, d.descend ? descValue : ascValue].join(' '))
                    .join(',');
            }
            return orders;
        },

        /* 生成请求的筛选参数 */
        getRequestFilters(defaultFilters) {
            const filters = {};
            const filterValue = this.tableFilters ?? defaultFilters;
            Object.keys(filterValue).forEach((key) => {
                const value = filterValue[key];
                if (value == null) {
                    return;
                }
                if (typeof value === 'string') {
                    if (value) {
                        filters[key] = value;
                    }
                } else if (Array.isArray(value)) {
                    if (value.length) {
                        filters[key] = value.join(',');
                    }
                } else {
                    filters[key] = value;
                }
            });
            return filters;
        },

        startLoading(){
            this.tableLoading = true;
        },

        endLoading(){
            this.tableLoading = false;
        }
    },
}
</script>