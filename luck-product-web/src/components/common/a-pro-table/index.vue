<template>
  <div>
    <a-table
        ref="tableWrapperRef"
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
        :showHeader="showHeader"
        :title="title"
        :customHeaderRow="customHeaderRow"
        :getPopupContainer="getPopupContainer"
        :transformCellText="transformCellText"
        :columns="tableColumns"
        :dataSource="tableData"
        :loading="tableLoading"
        :pagination="tablePagination"
        :scroll="tableScroll"
        :customRow="tableCustomRow"
        @expand="handleExpand"
        @change="handlePageChange"
        @expandedRowsChange="handleExpandedRowsChange"
    >
      <template v-for="column in tableColumns" :slot="column.scopedSlots?column.scopedSlots.customRender:''"
                slot-scope="text,record">
        <slot :name="column.scopedSlots?column.scopedSlots.customRender:''" v-bind:scope="record"></slot>
      </template>
      <template v-slot:action="text,record,index">
        <div v-if="index === hoverIndex" class="table-row-buttons">
          <div class="begin" style="display: inline-block"></div>
          <div class="buttons-contain">
            <button type="button" class="use-button--text" key="0" title="纠正信息">
              <span>
                <a-icon type="drag" />
                <div class="btn-name">纠正信息</div>
              </span>
            </button>
            <button type="button" class="use-button--text" key="1" title="查看入职办理">
              <span>
                <a-icon type="delete" />
                <div class="btn-name">查看入职办理</div>
              </span>
            </button>
          </div>
          <div class="fold-contain" style="display: none">
            <button type="button" class="el-button fold el-button--text">
              <span><svg aria-hidden="true" class="icon fs12"><use
                xlink:href="#s-right-put"></use></svg></span></button>
          </div>
        </div>
      </template>
    </a-table>
  </div>
</template>

<script>
import props from "@/components/common/a-pro-table/props";
import {eachTreeData, getFieldValue, getOrderItems, reloadData} from "@/components/common/a-pro-table/utils";
import {defaultResponseConfig} from "@/config/request";
import {deepClone} from "@/utils/common";

export default {
  name: 'AproTable',
  emits: ['expand', 'change', 'expandedRowsChange'],
  data() {
    return {
      // 当前显示数据
      tableData: [],
      // 数据请求状态
      tableLoading: this.loading,
      // 当前排序参数
      tableSorter: null,
      // 当前筛选参数
      tableFilters: null,
      // 请求错误后的提示信息
      errorText: '',
      // 悬浮列
      hoverIndex: '',
      // 分页配置
      tablePagination: {
        // 当前在第几页
        current: 1,
        // 每页显示数量
        pageSize: 10,
        // 数据总数量
        total: 0,
        // 分页大小
        pageSizeOptions: ['10', '20', '30', '40', '50'],
        // 切换页
        onChange: this.onPageCurrentChange
      }
    }
  },
  props: {
    ...props
  },
  computed: {
    /**
     *  滚动配置
     * @returns {Object|{y: number}}
     */
    tableScroll() {
      if (this.scroll) {
        return this.scroll;
      }
      return {
        y: 280
      };
    },

    tableColumns(){
        var columns = this.$utils.deepClone(this.columns);
        if(columns){
          columns.push({
              title: 'Action',
              key: 'action',
              colSpan: 0,
              scopedSlots: { customRender: 'action' },
          })
        }
        return columns;
    }
  },
  created() {

  },
  mounted() {
    this.reload();
  },
  methods: {

    /**
     * 分页变化
     * @param page
     * @param pageSize
     */
    onPageCurrentChange(page, pageSize) {
      this.tablePagination.current = page;
      this.tablePagination.pageSize = pageSize;
      this.reload();
    },

    /**
     *  行操作事件
      * @param record
     *  @param index
     *  @returns
     */
    tableCustomRow(record, index){
        return {
            props: {
                ...(this.customRow?.props ?? {})
            },
            on: { // 事件
                click: (event) => {},
                dblclick: (event) => {},
                contextmenu: (event) => {},
                mouseenter: (event) => {
                    this.handleMouseEnterRow(record,index);
                    if(this.customRow?.on?.mouseenter){
                        this.customRow.on.mouseenter(event);
                    }
                },
                mouseleave: (event) => {
                    this.handleMouseLeaveRow(record,index);
                    if(this.customRow?.on?.mouseleave){
                        this.customRow.on.mouseleave(event);
                    }
                }
            },
        }
    },

    /**
     *  鼠标离开表格行
      * @param record
     *  @param index
     */
    handleMouseEnterRow(record, index){
        this.hoverIndex = index;
    },

    /**
     *  鼠标悬浮表格行
      * @param record
     *  @param index
     */
    handleMouseLeaveRow(record, index){
        this.hoverIndex = '';
    },

    /**
     *  刷新表格数据
     */
    reload() {
      const defaultSorter = this.getDefaultSorter();
      const defaultFilters = this.getDefaultFilters();
      if (Array.isArray(this.dataSource)) {
        this.tableLoading = true;
        const {page, data, total} = reloadData(
            this.dataSource,
            this.tableSorter ?? defaultSorter,
            this.tablePagination.current,
            this.tablePagination.pageSize,
            this.needPage
        );
        this.tablePagination.current = page;
        this.tableData = data;
        this.tablePagination.total = total;
        this.tableLoading = false;
        const result = {data, total, response: this.dataSource};
        this.onRenderDone(result, page, total);
        return;
      }

      if (this.api.query) {
        if (typeof this.api.query === 'function') {
          this.tableLoading = true;
          // 排序参数
          const reqParams = {
            page: this.tablePagination.current,
            limit: this.tablePagination.pageSize,
            where: {...this.where},
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
                  const {data, count} = this.getResponseResult(result.data);
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
            this.tablePagination.current &&
            this.tablePagination.pageSize
        ) {
          const maxPage = Math.ceil(total / this.tablePagination.pageSize);
          if (maxPage && this.tablePagination.current > maxPage) {
            this.tablePagination.current = maxPage;
            this.reload();
            return;
          }
        }
        // 获取返回的数据
        this.tableData = data;
        this.tablePagination.total = total || data.length;
        this.tableLoading = false;
        const result = {
          data: this.tableData,
          total: this.tablePagination.total,
          response
        };
        this.onRenderDone(result, this.tablePagination.current, this.tablePagination.total);
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
     */
    onRenderDone(result, page, total) {
      this.$emit('done', result, page, total);
    },

    async getData(reqParams) {
      const {page, limit, where, order, filterValue, sorter, filters} = reqParams;
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
          searchData.searchParam['a'] = '1';
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
      const {dataName, countName, statusCode, statusName, msgName} = {
        ...defaultResponseConfig,
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
        const temp = {...d};
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
      const {sortName, orderName} = {
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

    handlePageChange(pagination, filters, sorter, {currentDataSource}) {
      this.$emit('change', pagination, filters, sorter, {currentDataSource})
    },

    handleExpand(expanded, record) {
      this.$emit('expand', expanded, record);
    },

    handleExpandedRowsChange(expandedRowKeys) {
      this.$emit('expandedRowsChange', expandedRowKeys);
    },

    startLoading() {
      this.tableLoading = true;
    },

    endLoading() {
      this.tableLoading = false;
    }
  },
}
</script>

<style lang="scss">
.table-row-buttons {
  position: absolute;
  z-index: 9;
  margin-bottom: 2px;
  display: inline-block;
  text-align: right;
  vertical-align: middle;
  right: 0;
  height: 52px;
  padding: 0px 30px 0px 10px;
  top: 0;
  margin-left: 80px;
  font-size: 0;
  border: 0 solid #6e8bde;
  white-space: nowrap;
  width: auto;
  transition: width 2s linear;
  -moz-transition: width 2s linear;
  -webkit-transition: width 2s linear;
  -o-transition: width 2s linear;
  background-color: #dbf1ec;
}

.table-row-buttons .begin {
  position: absolute;
  left: -80px;
  top: 0;
  height: 100%;
  width: 80px;
  background: -webkit-linear-gradient(0deg, rgba(219, 241, 236, 0), rgba(219, 241, 236, .85));
}

.table-row-buttons .buttons-contain {
  position: relative;
  display: inline-block;
  height: 100%;
}

.table-row-buttons .use-button--text {
  padding: 0 8px;
  border-width: 0;
  margin-right: 10px;
  margin-top: 0;
  height: 100%;
  font-size: 12px;
  background: none;
}

.table-row-buttons .btn-name {
  max-width: 105px;
  white-space: nowrap;
  text-overflow: ellipsis;
  -o-text-overflow: ellipsis;
  overflow: hidden;
}

.table-row-buttons .fold-contain {
  position: absolute;
  display: inline-block;
  right: 0;
  top: 0;
  padding: 0;
  margin: 0;
  height: 100%;
  width: 30px;
  cursor: pointer;
}

.ant-table-row  td:last-child{
  position: relative;
}

</style>
