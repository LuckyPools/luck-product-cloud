import commonProps from '../a-pro-common/props';

/**
 * 继承属性
 */
export const extendProps = {
    /**
     * 是否支持清除
     */
    allowClear: {
        type: Boolean,
        default: true
    },
    /**
     * 自动获取焦点
     */
    autoFocus: {
        type: Boolean,
        default: false
    },
    /**
     * 当此项为 true 时，点选每级菜单选项值都会发生变化
     */
    changeOnSelect: {
        type: Boolean,
        default: false
    },
    // /**
    //  * 默认的选中项
    //  */
    // defaultValue: {
    //     type: Array,
    //     default: () => []
    // },
    /**
     * 禁用
     */
    disabled: {
        type: Boolean,
        default: false
    },
    /**
     * 选择后展示的渲染函数
     */
    displayRender: {
        type: Function,
        default: ({labels, selectedOptions}) => labels.join(' / ')
    },
    /**
     * 次级菜单的展开方式，可选 'click' 和 'hover'
     */
    expandTrigger: {
        type: String,
        default: 'click',
        validator: (value) => ['click', 'hover'].includes(value)
    },
    /**
     * 自定义 options 中 label name children 的字段
     */
    fieldNames: {
        type: Object,
        default: () => ({
            label: 'name',
            value: 'code',
            children: 'children'
        })
    },
    /**
     * 菜单渲染父节点
     */
    getPopupContainer: {
        type: Function,
        default: () => document.body
    },
    /**
     * 用于动态加载选项
     */
    loadData: {
        type: Function,
        default: undefined
    },
    /**
     * 当下拉列表为空时显示的内容
     */
    notFoundContent: {
        type: String,
        default: 'Not Found'
    },
    /**
     * 可选项数据源
     */
    options: {
        type: Object,
        default: undefined
    },
    /**
     * 输入框占位文本
     */
    placeholder: {
        type: String,
        default: '请选择'
    },
    /**
     * 自定义浮层类名
     */
    popupClassName: {
        type: String,
        default: undefined
    },
    /**
     * 自定义浮层样式
     */
    popupStyle: {
        type: Object,
        default: () => ({})
    },
    /**
     * 浮层预设位置
     */
    popupPlacement: {
        type: String,
        default: 'bottomLeft',
        validator: (value) => [
            'bottomLeft', 'bottomRight', 'topLeft', 'topRight'
        ].includes(value)
    },
    /**
     * 控制浮层显隐
     */
    popupVisible: {
        type: Boolean,
        default: undefined
    },
    /**
     * 在选择框中显示搜索框
     */
    showSearch: {
        type: Boolean,
        default: false
    },
    /**
     * 输入框大小，可选 large default small
     */
    size: {
        type: String,
        default: 'default',
        validator: (value) => ['large', 'default', 'small'].includes(value)
    },
    /**
     * 自定义的选择框后缀图标
     */
    suffixIcon: {
        type: [String, Object],
        default: undefined
    },
    /**
     * 指定选中项
     */
    value: {
        type: Array,
        default: undefined
    }
}

/**
 * 自定义属性
 */
export const customProps = {

};

/**
 * 覆盖属性
 */
export const overrideProps = {
    /**
     * 获取选项数据的接口，无参
     * @return data 选项数组
     * @example listDictItems()
     */
    api: [String, Function],
    /**
     * 当API为URL字符串时的请求方法类型
     */
    apiMethod: {
        type: String,
        default: 'get'
    },
    /**
     * 当API为URL字符串时的请求参数
     */
    apiParams: {
        type: Object,
        default: () => {
            return {};
        }
    },
    /**
     * 字典过滤，和字典编码一起生效
     */
    dictFilter: {
        type: String,
        default: ''
    },
    /**
     * 字典父级ID，和字典编码一起生效
     */
    dictParentId: {
        type: String,
        default: ''
    },
    /**
     * 字典级别，和字典编码一起生效
     */
    dictGrade: {
        type: String,
        default: ''
    },
    /**
     * 禁用选项，由禁用值组成的数组
     */
    disabledOptions: {
        type: Array,
        default: () => {
            return [];
        }
    },
    /**
     * 字典编码，使用字典编码查询字典项作为选项数据
     */
    dictCode: {
        type: String,
        default: ''
    },
    /**
     * 选项标签的键名
     */
    labelKey: {
        type: String,
        default: 'name'
    },
    /**
     * 选项值的键名
     */
    valueKey: {
        type: String,
        default: 'code'
    },
    /**
     * 宽度
     */
    width: {
        type: String,
        default: '100%'
    }
};

export default {
    ...extendProps,
    ...customProps,
    ...overrideProps,
    /**
     * 公共属性
     */
    ...commonProps
};
