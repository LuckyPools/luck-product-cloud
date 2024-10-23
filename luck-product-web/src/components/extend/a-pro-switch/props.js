import commonProps from '../a-pro-common/props';

/**
 * 继承属性
 */
export const extendProps = {
    /**
     * 组件自动获取焦点
     */
    autoFocus: {
        type: Boolean,
        default: false
    },
    /**
     * 指定当前是否选中
     */
    checked: {
        type: Boolean,
        default: false
    },
    /**
     * 选中时的内容，可以是字符串或插槽
     */
    // checkedChildren: {
    //     type: [String, Function], // 在Vue中，可以使用Function来表示插槽
    //     default: null
    // },
    /**
     * 非选中时的内容，可以是字符串或插槽
     */
    // unCheckedChildren: {
    //     type: [String, Function],
    //     default: null
    // },
    /**
     * 初始是否选中
     */
    defaultChecked: {
        type: Boolean,
        default: false
    },
    /**
     * 是否禁用
     */
    disabled: {
        type: Boolean,
        default: false
    },
    /**
     * 加载中的开关
     */
    loading: {
        type: Boolean,
        default: false
    },
    /**
     * 开关大小，可选值：default small
     */
    size: {
        type: String,
        default: 'default',
        validator: (value) => {
            // 定义size的有效值
            const validSizes = ['default', 'small'];
            return validSizes.includes(value);
        }
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
    },
    inactiveValueLabel: {
        type: String,
        default: '否'
    },
    activeValueLabel: {
        type: String,
        default: '是'
    },
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
