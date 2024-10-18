/**
 * 封装组件共用属性
 */
export default {
  /**
   * 查看模式：view/edit/add
   */
  viewFlag: {
    type: String,
    default: ''
  },

  /**
   * 默认值
   */
  defaultValue: {
    type: String,
    default: ''
  },

  /**
   * 控件默认尺寸: small/large
   */
  size: {
    type: String,
    default: 'small'
  },

  /**
   * 占位符
   */
  placeholder: {
    type: String
  }
};
