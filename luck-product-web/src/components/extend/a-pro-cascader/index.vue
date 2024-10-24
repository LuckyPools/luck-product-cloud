<template>
  <div>
    <span v-if="isView" class="a-view">{{ viewValue }}</span>
    <a-cascader
        :allow-clear="allowClear"
        :auto-focus="autoFocus"
        :change-on-select="changeOnSelect"
        :disabled="disabled"
        :display-render="displayRender"
        :expand-trigger="expandTrigger"
        :field-names="fieldNames"
        :get-popup-container="getPopupContainer"
        :load-data="loadData"
        :not-found-content="notFoundContent"
        :options="localOptions"
        :placeholder="placeholder"
        :popup-class-name="popupClassName"
        :popup-style="popupStyle"
        :popup-placement="popupPlacement"
        :popup-visible.sync="popupVisible"
        :show-search="showSearch"
        :size="size"
        :suffix-icon="suffixIcon"
        v-model="selectValue"
    >

    </a-cascader>
  </div>
</template>

<script>
import props from './props';
import common from '../a-pro-common/mixin';

export default {
  name: 'AProCascader',
  mixins: [common],
  props: {
    ...props
  },
  emits: [],
  data() {
    return {
      // 选项
      localOptions: [],
      // 绑定数据
      selectValue: ''
    };
  },
  computed: {
    // 查看模式显示数据
    viewValue() {
      if (!this.value) {
        return '';
      }
      let selectValue = this.value.toString().split(',');
      let selectLabel = [];
      this.localOptions.forEach((item) => {
        if (selectValue.includes(item[this.valueKey].toString())) {
          selectLabel.push(item[this.labelKey]);
        }
      });
      return selectLabel.join(',');
    }
  },
  watch: {
    value: {
      handler() {
        this.setSelectValue();
      }
    },
    selectValue: {
      handler() {
        if (this.isMultiple) {
          this.$emit('input', this.selectValue.join(','));
        } else {
          this.$emit('input', this.selectValue);
        }
      },
      deep: true
    },
    localOptions: {
      handler() {
        this.setSelectValue();
      },
      deep: true
    },
    dictCode: {
      handler() {
        if (this.dictCode) {
          this.loadOptions();
        }
      }
    },
    dictFilter: {
      handler() {
        if (this.dictCode) {
          this.loadOptions();
        }
      }
    },
    dictParentId: {
      handler() {
        if (this.dictCode) {
          this.loadOptions();
        }
      }
    },
    dictGrade: {
      handler() {
        if (this.dictCode) {
          this.loadOptions();
        }
      }
    },
    options: {
      handler() {
        this.loadOptions();
      },
      deep: true
    },
    api: {
      handler() {
        if (this.api) {
          this.loadOptions();
        }
      },
      deep: true
    },
    apiParams: {
      handler() {
        this.loadOptions();
      },
      deep: true
    }
  },
  created() {
  },
  mounted() {
    this.setDefaultValue();
    this.loadOptions();
  },
  methods: {
    /**
     * 加载数据或选项
     */
    loadOptions() {
      let that = this;
      Promise.resolve(this.queryData())
          .then((data) => {
            that.localOptions = data;
          })
          .catch((e) => {
            console.error('获取数据失败：' + e);
            that.$message.error('获取数据失败');
          });
    },

    /**
     * 获取数据或选项
     */
    queryData() {
      if (this.api) {
        if (typeof this.api === 'string') {
          return this.queryUrl();
        } else {
          return this.api();
        }
      } else if (this.dictCode != null && this.dictCode.trim() !== '') {
        return this.queryDict();
      } else {
        return this.$utils.deepClone(this.options);
      }
    },

    /**
     * api为string类型时的取数方法
     */
    async queryUrl() {
      let req = this.$api?.httpRequest;
      if (req && this.apiMethod && this.api) {
        let result = await req(this.apiMethod, this.api, this.apiParams);
        return result.data;
      } else {
        return [];
      }
    },

    /**
     * 查询字典
     */
    async queryDict() {
      let req = this.$api?.dictDataSource;
      if (req) {
        let params = {};
        params.dictCode = this.dictCode;
        params.filter = this.dictFilter;
        params.parentId = this.dictParentId;
        params.grade = this.dictGrade;
        let result = await req(params);
        return result.data;
      } else {
        return {data: []};
      }
    },

    /**
     * 设置本地绑定值
     */
    setSelectValue() {
      if (this.isMultiple) {
        let curValue = this.value ? this.value.split(',') : [];
        let selectValue = [];
        curValue.forEach((it) => {
          if (this.localOptions.some((item) => item[this.valueKey] === it)) {
            selectValue.push(it);
          }
        });
        this.selectValue = selectValue;
      } else {
        if (this.localOptions.some((item) => item[this.valueKey] === this.value)) {
          this.selectValue = this.value ? this.value : null;
        } else {
          this.selectValue = null;
        }
      }
    },

    /**
     * 禁用项
     * @param item 选项
     */
    isDisabledOption(item) {
      if (
          item == null ||
          this.disabledOptions == null ||
          this.disabledOptions.length === 0
      ) {
        return false;
      }
      let currentItemValue = item[this.valueKey];
      return this.disabledOptions.includes(currentItemValue);
    },

    /**
     * 失去焦点时触发的回调
     */
    handleBlur() {
      this.$emit('blur');
    },
    /**
     * 选中 option，或 input 的 value 变化时触发的回调
     * @param {string} value - 选中项的值
     * @param {Option|Array<Option>} option - 选中项的对象
     */
    handleChange(value, option) {
      this.$emit('change', value, option);
    },
    /**
     * 取消选中时触发的回调
     * @param {string} value - 取消选中项的值
     * @param {Option} option - 取消选中项的对象
     */
    handleDeselect(value, option) {
      this.$emit('deselect', value, option);
    },
    /**
     * 获得焦点时触发的回调
     */
    handleFocus() {
      this.$emit('focus');
    },
    /**
     * 键盘按下时触发的回调
     */
    handleInputKeydown() {
      this.$emit('inputKeydown');
    },
    /**
     * 鼠标移入时触发的回调
     */
    handleMouseenter() {
      this.$emit('mouseenter');
    },
    /**
     * 鼠标移出时触发的回调
     */
    handleMouseleave() {
      this.$emit('mouseleave');
    },
    /**
     * 下拉列表滚动时触发的回调
     */
    handlePopupScroll() {
      this.$emit('popupScroll');
    },
    /**
     * 文本框值变化时触发的回调
     * @param {string} value - 文本框的值
     */
    handleSearch(value) {
      this.$emit('search', value);
    },
    /**
     * 被选中时触发的回调
     * @param {string} value - 选中项的值
     * @param {Option} option - 选中项的对象
     */
    handleSelect(value, option) {
      this.$emit('select', value, option);
    },
    /**
     * 展开下拉菜单时触发的回调
     * @param {boolean} open - 下拉菜单是否展开
     */
    handleDropdownVisibleChange(open) {
      this.$emit('dropdownVisibleChange', open);
    }
  }
};
</script>

<style scoped></style>
