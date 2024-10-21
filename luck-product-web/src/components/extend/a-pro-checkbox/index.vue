<template>
    <div class="el-bns">
        <span v-if="isView" class="a-view">{{ viewValue }}</span>
        <template v-else>
            <a-checkbox-group
                :defaultValue="defaultValue"
                :disabled="disabled"
                :name="name"
                v-model="selectValue"
                @change="handleChange"
            >
                <a-checkbox
                    v-for="item in localOptions"
                    :disabled="isDisabledOption(item)"
                    @change="(val) => handleChangeItem(val, item)"
                    @blur="handleBlur"
                    @focus="handleFocus"
                >
                    {{ item[labelKey] }}
                </a-checkbox>
            </a-checkbox-group>
        </template>
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
    emits: ['change', 'select'],
    data() {
        return {
            // 选项
            localOptions: [],
            // 绑定数据
            selectValue: []
        };
    },
    computed: {
        // 查看模式显示数据
        viewValue() {
            let selectLabel = [];
            this.localOptions.forEach((item) => {
                if (this.selectValue.includes(item[this.valueKey])) {
                    selectLabel.push(item[this.labelKey]);
                }
            });
            if (selectLabel.length > 0) {
                return selectLabel.join(',');
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
                // 监听checkbox选项的变化，当选项发生变化时，重新绑定已选中的值
                this.setSelectValue();
            },
            deep: true
        },
        dictCode: {
            handler() {
                // 当字典码发生变化时，重新渲染checkbox选项
                if (this.dictCode) {
                    this.loadOptions();
                }
            }
        },
        dictFilter: {
            handler() {
                // 当字典过滤条件发生变化时，重新渲染checkbox选项
                if (this.dictCode) {
                    this.loadOptions();
                }
            }
        },
        dictParentId: {
            handler() {
                // 当指定字典项父级id发生变化时，重新渲染checkbox选项
                if (this.dictCode) {
                    this.loadOptions();
                }
            }
        },
        dictGrade: {
            handler() {
                // 当指定字典项层级发生变化时，重新渲染checkbox选项
                if (this.dictCode) {
                    this.loadOptions();
                }
            }
        },
        options: {
            handler() {
                // 当指定本地字典项变化时，重新渲染checkbox选项
                this.loadOptions();
            },
            deep: true
        },
        api: {
            handler() {
                // 当指定取数的api变化时，重新渲染checkbox选项
                if (this.api) {
                    this.loadOptions();
                }
            },
            deep: true
        },
        apiParams: {
            handler() {
                // 当指定取数的api参数变化时，重新渲染checkbox选项
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
                return this.$utils.deepClone(this.options);
            }
        },

        /**
         * api为string类型时的取数方法
         */
        async queryUrl() {
            let req = this.$api?.httpRequest;
            if (req && this.method && this.api) {
                const result = await req(this.apiMethod, this.api, this.apiParams);
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
                params.grades = this.dictGrade;
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
            if (this.value) {
                let curValue = this.value ? this.value.split(',') : [];
                let selectValue = [];
                curValue.forEach((it) => {
                    if (this.localOptions.some((item) => item[this.valueKey] === it)) {
                        selectValue.push(it);
                    }
                });
                this.selectValue = selectValue;
            } else {
                this.selectValue = [];
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
         * 选中 option，或 input 的 value 变化时触发的回调
         * @param {string} value - 选中项的值
         * @param {Option|Array<Option>} option - 选中项的对象
         */
        handleChange(e) {
            this.$emit('change', e);
        },

        /**
         * group和box的change事件重复
         * box项修改提交为select事件
         * @param val 是否选中
         * @param option 选中项
         */
        handleChangeItem(val, option) {
            this.$emit('changeItem', val, option);
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
