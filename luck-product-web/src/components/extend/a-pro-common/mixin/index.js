/**
 * 混用组件，包含了组件间的公共属性和方法
 */
const common = {
  beforeCreate() {

  },
  computed: {
    isView() {
      return this.viewFlag === 'view';
    }
  },
  methods: {
    /**
     * 设置默认值
     */
    setDefaultValue() {
      if (this.defaultValue && !this.value) {
        this.$emit('input', this.defaultValue);
      }
    }
  }
};

export default common;
