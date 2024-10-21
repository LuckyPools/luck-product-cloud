<template>
    <div>
        <span v-if="isView" class="el-bns-view">{{ viewValue }}</span>
        <div v-else :style="{ width: width }">
            <a-radio-group
                name="radioGroup"
                v-model="selectValue"
                :defaultValue="defaultValue"
                :disabled="disabled"
                :name="name"
                :size="size"
                :buttonStyle="buttonStyle"
                @change="handleChange">
                <a-radio v-for="item in localOptions"
                         :value="item[valueKey]"
                         :disabled="isDisabledOption(item)"
                         @blur="handleBlur"
                         @focus="handleFocus">
                    {{ item[labelKey] }}
                </a-radio>
            </a-radio-group>
        </div>
    </div>
</template>

<script>
import props from './props';
import common from '../a-pro-common/mixin';

export default {
    mixins: [common],
    props: {
        ...props
    },
    emits: ['input'],
    data() {
        return {
            // 选项
            localOptions: [],
            // 选择项
            selectValue: null
        };
    },
    computed: {
        // 查看模式显示数据
        viewValue() {
            let option = this.localOptions.find(
                (item) => this.value == item[this.valueKey]
            );
            if (option) {
                return option[this.labelKey];
            } else {
                return '';
            }
        }
    },
    watch: {
        selectValue: {
            handler(newVal, oldVal) {
                if (newVal !== oldVal) {
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
        filter: {
            handler() {
                if (this.dictCode) {
                    this.loadOptions();
                }
            }
        },
        parentId: {
            handler() {
                if (this.dictCode) {
                    this.loadOptions();
                }
            }
        },
        grades: {
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
                return this.$utils.deepClone(this.localOptions);
            }
        },

        /**
         * api为string类型时的取数方法
         */
        async queryUrl() {
            let req = this.$api?.httpRequest;
            if (req && this.method && this.api) {
                const result = await req(this.method, this.api, this.params);
                return result.data;
            } else {
                return {};
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
                params.filter = this.filter;
                params.parentId = this.parentId;
                params.grades = this.grades;
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
            if (!this.localOptions.some((item) => item[this.valueKey] == this.value)) {
                this.selectValue = this.value;
            }
        },

        /**
         * 禁用项
         * @param item 选项
         */
        isDisabledOption(item) {
            if (this.disabled) {
                return true;
            }
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
         * 选中 option，或 input 的 value 变化时触发的回调
         * @param {string} value - 选中项的值
         * @param {Option|Array<Option>} option - 选中项的对象
         */
        handleChange(e) {
            this.$emit('change', e);
        },

        /**
         * 获取焦点
         */
        handleFocus(){
            this.$emit('focus');
        },

        /**
         * 移除焦点
         */
        handleBlur(){
            this.$emit('blur');
        }
    }
};
</script>

<style scoped></style>
