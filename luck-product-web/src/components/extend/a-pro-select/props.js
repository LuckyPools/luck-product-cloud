import commonProps from '../a-pro-common/props';

/**
 * 继承属性
 */
export const extendProps = {
    value: null,
    prefixCls: String,
    /**
     * 大小：small/large/default
     */
    size: String,
    showAction: [String, Array],
    notFoundContent: {
        type: null,
        default: 'Not Found'
    },
    transitionName: String,
    choiceTransitionName: String,
    showSearch: Boolean,
    allowClear: {
        type: Boolean,
        default: false
    },
    disabled: Boolean,
    tabIndex: Number,
    placeholder: {
        type: null
    },
    defaultActiveFirstOption: {
        type: Boolean,
        default: true
    },
    dropdownClassName: String,
    dropdownStyle: {
        type: null
    },
    dropdownMenuStyle: {
        type: null
    },
    dropdownMatchSelectWidth: {
        type: Boolean,
        default: true
    },
    filterOption: {
        type: [Boolean, Function],
        default: true
    },
    autoFocus: {
        type: Boolean,
        default: false
    },
    backfill: Boolean,
    showArrow: {
        type: Boolean,
        default: true
    },
    getPopupContainer: {
        type: Function,
        default: () => document.body
    },
    // open: Boolean,
    defaultOpen: Boolean,
    autoClearSearchValue: {
        type: Boolean,
        default: true
    },
    dropdownRender: Function,
    loading: Boolean,

    mode: String,
    optionLabelProp: String,
    firstActiveValue: [String, Array],
    maxTagCount: Number,
    maxTagPlaceholder: {
        type: null
    },
    maxTagTextLength: Number,
    optionFilterProp: String,
    labelInValue: Boolean,
    tokenSeparators: Array,
    getInputElement: Function,
    options: Array,
    suffixIcon: {
        type: null
    },
    removeIcon: {
        type: null
    },
    clearIcon: {
        type: null
    },
    menuItemSelectedIcon: {
        type: null
    }
}

/**
 * 自定义属性
 */
export const customProps = {
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
}

export default {
    ...extendProps,
    ...customProps,
    /**
     * 公共属性
     */
    ...commonProps
};
