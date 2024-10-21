import commonProps from '../a-pro-common/props';
/**
 * 继承属性
 */
export const extendProps = {
    /**
     * 默认选中的值
     */
    defaultValue: {
        type: null, // 'any' type is represented by null in Vue prop type definition
        default: null
    },
    /**
     * 禁选所有子单选器
     */
    disabled: {
        type: Boolean,
        default: false
    },
    /**
     * RadioGroup 下所有 input[type="radio"] 的 name 属性
     */
    name: {
        type: String,
        default: null
    },
    /**
     * 以配置形式设置子元素
     */
    options: {
        type: [Array, String],
        default: null
    },
    /**
     * 大小，只对按钮样式生效
     */
    size: {
        type: String,
        default: 'default' // Assuming the default size is 'default'
    },
    /**
     * 用于设置当前选中的值
     */
    value: {
        type: null, // 'any' type is represented by null in Vue prop type definition
        default: null
    },
    /**
     * RadioButton 的风格样式，目前有描边和填色两种风格
     */
    buttonStyle: {
        type: String,
        default: 'outline'
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
    method: {
        type: String,
        default: 'get'
    },
    /**
     * 当API为URL字符串时的请求参数
     */
    params: {
        type: Object,
        default: () => {
            return {};
        }
    },
    /**
     * 字典过滤，和字典编码一起生效
     */
    filter: {
        type: String,
        default: ''
    },
    /**
     * 字典父级ID，和字典编码一起生效
     */
    parentId: {
        type: String,
        default: ''
    },
    /**
     * 字典级别，和字典编码一起生效
     */
    grades: {
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
