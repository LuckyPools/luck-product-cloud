<template>
  <div>
    <span v-if="isView" class="a-view">{{ viewValue }}</span>
    <a-select
        v-else
        ref="select"
        :prefixCls="prefixCls"
        :size="size"
        :showAction="showAction"
        :notFoundContent="notFoundContent"
        :transitionName="transitionName"
        :choiceTransitionName="choiceTransitionName"
        :showSearch="showSearch"
        :allowClear="allowClear"
        :disabled="disabled"
        :tabIndex="tabIndex"
        :placeholder="placeholder"
        :defaultActiveFirstOption="defaultActiveFirstOption"
        :dropdownClassName="dropdownClassName"
        :dropdownStyle="dropdownStyle"
        :dropdownMenuStyle="dropdownMenuStyle"
        :dropdownMatchSelectWidth="dropdownMatchSelectWidth"
        :filterOption="filterOption"
        :autoFocus="autoFocus"
        :backfill="backfill"
        :showArrow="showArrow"
        :getPopupContainer="getPopupContainer"
        :defaultOpen="defaultOpen"
        :autoClearSearchValue="autoClearSearchValue"
        :dropdownRender="dropdownRender"
        :loading="loading"
        :mode="mode"
        :optionLabelProp="optionLabelProp"
        :firstActiveValue="firstActiveValue"
        :maxTagCount="maxTagCount"
        :maxTagPlaceholder="maxTagPlaceholder"
        :maxTagTextLength="maxTagTextLength"
        :optionFilterProp="optionFilterProp"
        :labelInValue="labelInValue"
        :tokenSeparators="tokenSeparators"
        :getInputElement="getInputElement"
        :suffixIcon="suffixIcon"
        :removeIcon="removeIcon"
        :clearIcon="clearIcon"
        :menuItemSelectedIcon="menuItemSelectedIcon"
        :style="{ width: width }"
        v-model="selectValue"
        @blur="handleBlur"
        @change="handleChange"
        @deselect="handleDeselect"
        @focus="handleFocus"
        @inputKeydown="handleInputKeydown"
        @mouseenter="handleMouseenter"
        @mouseleave="handleMouseleave"
        @popupScroll="handlePopupScroll"
        @search="handleSearch"
        @select="handleSelect"
        @dropdownVisibleChange="handleDropdownVisibleChange"
    >
        <!-- 空数据插槽 -->
<!--        <template v-slot:notFoundContent>-->
<!--            <slot name="notFoundContent"></slot>-->
<!--        </template>-->
        <a-select-option
            v-for="item in localOptions"
            :key="item[valueKey]"
            :title="item[labelKey]"
            :value="item[valueKey]"
            :disabled="isDisabledOption(item)"
        >
            <!-- 默认插槽 -->
            <span style="float: left">{{ item[labelKey] }}</span>
        </a-select-option>
    </a-select>
  </div>
</template>

<script>
import props from './props';
import common from '../a-pro-common/mixin';

export default {
    name: 'AproSelect',
    mixins: [common],
    props: {
        ...props
    },
    emits: [
        'blur',
        'change',
        'deselect',
        'focus',
        'inputKeydown',
        'mouseenter',
        'mouseleave',
        'popupScroll',
        'search',
        'select',
        'dropdownVisibleChange'
    ],
    data() {
        let selectValue = '';
        if (this.mode === 'multiple') {
            selectValue = [];
        }
        return {
            // 选项
            localOptions: [],
            // 绑定数据
            selectValue: selectValue
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
        },
        // 是否多选
        isMultiple(){
            return this.mode === 'multiple'
        }
    },
    watch: {
        selectValue: {
            handler(newVal, oldVal) {
                if (newVal !== oldVal) {
                    if (this.isMultiple) {
                        this.$emit('input', this.selectValue.join(','));
                    } else {
                        this.$emit('input', this.selectValue);
                    }
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
        params: {
            handler() {
                this.loadOptions();
            },
            deep: true
        }
    },
    created() {},
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
                    that.$message.error( '获取数据失败');
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
                return { data: [] };
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
                    if (this.localOptions.some((item) => item[this.valueKey] == it)) {
                        selectValue.push(it);
                    }
                });
                this.selectValue = selectValue;
            } else {
                if (this.localOptions.some((item) => item[this.valueKey] == this.value)) {
                    this.selectValue = this.value ? this.value : '';
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
                this.disabledOptions.length == 0
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
