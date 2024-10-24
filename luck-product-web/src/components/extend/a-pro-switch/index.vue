<template>
  <div>
    <span v-if="isView" class="a-view">{{ viewValue }}</span>
    <div v-else>
      <a-switch
          v-model="selectValue"
          :auto-focus="autoFocus"
          :default-checked="defaultChecked"
          :disabled="disabled"
          :loading="loading"
          :size="size"
          @change="handleChange"
          @blur="handleBlur"
          @focus="handleFocus"
      >
        <template v-slot:checkedChildren>
          <slot name="checkedChildren">{{localCheckedChildren}}</slot>
        </template>
        <template v-slot:unCheckedChildren>
          <slot name="unCheckedChildren">{{localUnCheckedChildren}}</slot>
        </template>
      </a-switch>
    </div>
  </div>
</template>

<script>
import props from './props';
import common from '../a-pro-common/mixin';

export default {
    name: 'AProSwitch',
    mixins: [common],
    props: {
      ...props
    },
    emits: ['change','blur','focus'],
    data() {
      return {
        // 选项
        localOptions: [],
        localCheckedChildren: '',
        localUnCheckedChildren: '',
        selectValue: null
      };
    },
    computed: {
        // 查看模式显示数据
        viewValue() {
          return this.value === this.activeValue
              ? this.activeValueLabel
              : this.inactiveValueLabel;
        }
    },
    watch: {
      value: {
          handler() {
             this.setSelectValue();
          },
      },
      selectValue: {
          handler() {
              if(this.localOptions && this.localOptions.length > 1){
                let selectValue;
                if(this.selectValue){
                   selectValue = this.localOptions[1][this.valueKey];
                }else{
                   selectValue = this.localOptions[0][this.valueKey];
                }
                this.$emit('input', selectValue);
              }
          },
          deep: true
      },
      localOptions: {
          handler() {
              if(this.localOptions && this.localOptions.length > 1){
                  this.localUnCheckedChildren = this.localOptions[0][this.labelKey];
                  this.localCheckedChildren = this.localOptions[1][this.labelKey];
              }
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
            this.selectValue = this.localOptions[1][this.valueKey] === this.value;
        },

        /**
         * 选中 option，或 input 的 value 变化时触发的回调
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
