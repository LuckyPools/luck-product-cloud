export const tableProps = {
    prefixCls: String,
    dropdownPrefixCls: String,
    rowSelection: Object,
    pagination: Object,
    size: String,
    dataSource: Array,
    components: Object,
    columns: Array,
    rowKey: String | Function,
    rowClassName: Function,
    expandedRowRender: null,
    defaultExpandAllRows: Boolean,
    defaultExpandedRowKeys: Array,
    expandedRowKeys: Array,
    expandIconAsCell: Boolean,
    expandIconColumnIndex: Number,
    expandRowByClick: Boolean,
    // onExpandedRowsChange?: (expandedRowKeys: string[] | number[]) => void;
    //  onExpand?: (expanded: boolean, record: T) => void;
    // onChange?: (pagination: PaginationProps | boolean, filters: string[], sorter: Object) => any;
    loading: Boolean,
    locale: String,
    indentSize: Number,
    // onRowClick?: (record: T, index: number, event: Event) => any;
    customRow: Function,
    customHeaderRow: Function,
    useFixedHeader: Boolean,
    bordered: Boolean,
    showHeader: Boolean,
    footer: Function,
    title: Function,
    scroll: Object,
    childrenColumnName: Array | String,
    bodyStyle: null,
    sortDirections: Array,
    tableLayout: String,
    getPopupContainer: Function,
    expandIcon: Function,
    transformCellText: Function
}

export const customProps= {
    /**
     * 数据API/URL/方法
     */
    api: {
        type: Object,
        default() {
            return {
                /**
                 * 获取列表数据的接口
                 * <p>可以是url或function，推荐使用function更方便传参</p>
                 * @param searchData 由页数大小查询条件等数据组成的对象
                 * @return tableVO 返回值约定为后台的tableVO对象
                 */
                query: '',
                /**
                 * 默认关键字删除接口
                 * <p>可以是url或function，推荐使用function更方便传参</p>
                 * <p>当不定义本属性时，且由不定义customDelete时，使用默认删除功能(rowBtn,toolkit中的delete)时，会抛异常</p>
                 * @param id 行数据关键字，由primaryKey指定
                 * @return msg 返回操作结果提示语
                 */
                delete: '',
                /**
                 * 自定义操作列单条删除方法。用于完全重写删除逻辑，不走组件给定的默认删除逻辑(如默认删除完后刷新列表)
                 * <p>必须为function</p>
                 * @param row 行数据
                 */
                customDelete: '',
                /**
                 * 自定义批量删除方法。用于完全重写批量删除逻辑，不走组件给定的默认删除逻辑(如默认删除完后刷新列表)
                 * <p>必须为function</p>
                 * @param selection 选择数据
                 */
                customBatchDelete: '',
                /**
                 * 自定义编辑方法
                 * <p>可以是url或function。当为url时，编辑页面将会通过路由的方式在新tab中打开；当为function时，编辑页面及参数、状态传递将全权交由开发人员自定义</p>
                 * <p>本属性不是一定要定义的，当未定义本属性时，组件会走默认逻辑完成formStream对象的状态更新。至于formStream状态更新后触发的效果则由开发人员自定义</p>
                 * @param row 行数据
                 */
                edit: '',
                /**
                 * 自定义新增方法
                 * <p>可以是url或function。当为url时，编辑页面将会通过路由的方式在新tab中打开；当为function时，编辑页面及参数、状态传递将全权交由开发人员自定义</p>
                 * <p>本属性不是一定要定义的，当未定义本属性时，组件会走默认逻辑完成formStream对象的状态更新。至于formStream状态更新后触发的效果则由开发人员自定义</p>
                 * @param row 行数据
                 */
                add: ''
            };
        }
    },

    // 是否需要分页
    needPage: {
        type: Boolean,
        default: true
    },

    // 默认的排序列的 prop 和顺序
    defaultSort: Object,
    // 服务端数据源自定义请求参数名称
    request: Object,
    // 服务端数据源自定义响应参数名称
    response: Object,  // 服务端数据源升序的排序值
    ascendValue: String,
    // 服务端数据源降序的排序值
    descendValue: String,
    // 是否自动修正页码
    autoAmendPage: {
        type: Boolean,
        default: true
    },
    // 服务端数据源自定义响应数据解析
    parseData: Function,
    // 渲染嵌套数据的配置选项
    treeProps: {
        type: Object,
        default() {
            return {
                hasChildren: 'hasChildren',
                children: 'children'
            };
        }
    }
}

export default {
    ...tableProps,
    ...customProps
};
