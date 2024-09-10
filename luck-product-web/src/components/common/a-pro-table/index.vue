<template>
  <div>
      <a-table
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
          :size="size"
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
          :columns="columns"
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
import props from "@/components/common/a-pro-table/props";
import {eachTreeData, getFieldValue, getOrderItems, reloadData} from "@/components/common/a-pro-table/utils";

export default {
    name: 'AproTable',
    data() {
        return {
          testData,
          // 当前显示数据
          tableData: [],
          // 数据请求状态
          tableLoading: this.loading,
          // 当前在第几页
          tablePage: this.currentPage,
          // 每页显示数量
          tableLimit: this.pageSize,
          // 当前排序参数
          tableSorter: null,
          // 当前筛选参数
          tableFilters: null,
          // 数据总数量
          tableTotal: 0,
          // 当前搜索参数
          tableWhere: {},
          // 请求错误后的提示信息
          errorText: '',
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
      this.reload();
    },
    methods: {

      reload() {
          const defaultSorter = this.getDefaultSorter();
          const defaultFilters = this.getDefaultFilters();
          if (Array.isArray(this.dataSource)) {
              this.tableLoading = true;
              const { page, data, total } = reloadData(
                  this.dataSource,
                  this.tableSorter ?? defaultSorter,
                  this.tablePage,
                  this.tableLimit,
                  this.needPage
              );
              this.tablePage = page;
              this.tableData = data;
              this.tableTotal = total;
              this.tableLoading = false;
              const result = { data, total, response: this.dataSource };
              this.onRenderDone(result, page, total);
              return;
          }

          if (this.api.query) {
              if (typeof this.api.query === 'function') {
                  this.tableLoading = true;
                  // 排序参数
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
                      filters: this.tableFilters ?? defaultFilters
                  };
                  const req = this.getData(reqParams);
                  Promise.resolve(req)
                      .then((result) => {
                          if (typeof result !== 'undefined') {
                              const { data, count } = this.getResponseResult(result);
                              this.dataSourceCallback(data, count, result);
                          }
                      })
                      .catch((e) => {
                          this.dataSourceCallback(e?.message);
                          console.error(e);
                      });
              }
          }
      },

      /**
       * funtion
       * @param data
       * @param total
       * @param response
       */
      dataSourceCallback(data, total, response) {
          if (Array.isArray(data)) {
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
              console.error('dataSource返回的数据错误: ', data);
          }
      },

      /**
       * 表格数据渲染完成
       * @param result
       * @param page
       * @param total
       * @param parent
       */
      onRenderDone(result, page, total, parent) {
          this.$emit('done', result, page, total, parent);
      },

      async getData(page, limit, where, order, filterValue, sorter, filters) {
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

      /**
       * 获取请求的数据和总数量
       * @param data
       * @returns
       */
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


      /**
       * 树形懒加载时给数据增加可展开的字段标识
       * @param data
       * @returns {*}
       */
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

      /**
       * 获取默认排序方式
       * @returns {Object}
       */
      getDefaultSorter() {
          return this.defaultSort;
      },

      /**
       * 获取默认的筛选条件
       * @returns {{}}
       */
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

      /**
       * 生成请求的排序参数
       * @param defaultSorter
       * @returns {{}}
       */
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

      /**
       * 生成请求的筛选参数
       * @param defaultFilters
       * @returns {{}}
       */
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
