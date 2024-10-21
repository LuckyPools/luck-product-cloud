import commonProps from '../a-pro-common/props';

/**
 * 继承属性
 */
export const extendProps = {
    /**
     * 默认选中的选项
     */
    defaultValue: {
        type: Array,
        default: () => []
    },
    /**
     * 整组失效
     */
    disabled: {
        type: Boolean,
        default: false
    },
    /**
     * CheckboxGroup 下所有 input[type="checkbox"] 的 name 属性
     */
    name: {
        type: String,
        default: null
    },
    /**
     * 指定可选项，可以通过 slot="label" slot-scope="option" 定制label
     */
    options: {
        type: [Array, String],
        default: () => []
    },
    /**
     * 指定选中的选项
     */
    value: {
        type: Array,
        default: () => []
    }
};

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
     * 字典编码，使用字典编码查询字典项作为选项数据
     */
    dictCode: {
        type: String,
        default: ''
    },
    /**
     * 字典过滤，和字典编码一起生效
     */
    dictFilter: {
        type: Array[Number],
        default: () => []
    },
    /**
     * 字典项父级ID，和字典编码一起生效
     */
    dictParentId: {
        type: String,
        default: ''
    },
    /**
     * 字典层级，和字典编码一起生效
     */
    dictGrade: {
        type: Array[Number],
        default: () => []
    },
    /**
     * 禁用选项，由禁用值组成的数组
     */
    disabledOptions: {
        type: Array,
        default: () => []
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

/**
 * 覆盖属性
 */
export const overrideProps = {
    value: String
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
