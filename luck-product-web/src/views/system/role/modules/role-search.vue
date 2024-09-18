<template>
  <ACard :title="$t('common.search')" :bordered="false" class="card-wrapper">
    <AForm
        ref="formRef"
        :model="model"
        :rules="rules"
        :label-col="{
        span: 5,
        md: 7
      }"
        class="ant-advanced-search-form"
    >
      <ARow :gutter="[16, 16]" class="ant-search-form-row" wrap>
        <ACol :span="24" :md="12" :lg="6">
          <AFormItem :label="$t('page.manage.user.userName')" name="username" class="m-0">
            <AInput v-model:value="model.username" :placeholder="$t('page.manage.user.form.userName')" />
          </AFormItem>
        </ACol>
        <ACol :span="24" :md="12" :lg="6">
          <AFormItem :label="$t('page.manage.user.userGender')" name="userGender" class="m-0">
            <ASelect
                v-model:value="model.userGender"
                :placeholder="$t('page.manage.user.form.userGender')"
                :options="translateOptions(userGenderOptions)"
                clearable
            />
          </AFormItem>
        </ACol>
        <ACol :span="24" :md="12" :lg="6">
          <AFormItem :label="$t('page.manage.user.nickName')" name="nickName" class="m-0">
            <AInput v-model:value="model.nickName" :placeholder="$t('page.manage.user.form.nickName')" />
          </AFormItem>
        </ACol>
        <ACol :span="24" :md="12" :lg="6">
          <AFormItem :label="$t('page.manage.user.userPhone')" name="userPhone" class="m-0">
            <AInput v-model:value="model.userPhone" :placeholder="$t('page.manage.user.form.userPhone')" />
          </AFormItem>
        </ACol>
        <ACol :span="24" :md="12" :lg="6">
          <AFormItem :label="$t('page.manage.user.userEmail')" name="userEmail" class="m-0">
            <AInput v-model:value="model.userEmail" :placeholder="$t('page.manage.user.form.userEmail')" />
          </AFormItem>
        </ACol>
        <ACol :span="24" :md="12" :lg="6">
          <AFormItem :label="$t('page.manage.user.userStatus')" name="userStatus" class="m-0">
            <ASelect
                v-model:value="model.status"
                :placeholder="$t('page.manage.user.form.userStatus')"
                :options="translateOptions(enableStatusOptions)"
                clearable
            />
          </AFormItem>
        </ACol>
        <div class="flex-1 use-btn">
          <AFormItem>
            <div class="w-full flex-y-center justify-end gap-12px">
              <AButton @click="reset">
                <span class="ml-8px">{{ $t('common.reset') }}</span>
              </AButton>
              <AButton type="primary" ghost @click="search">
                <span class="ml-8px">{{ $t('common.search') }}</span>
              </AButton>
            </div>
          </AFormItem>
        </div>
      </ARow>
    </AForm>
    <SvgIcon icon="icon-ic-round-search" class="align-sub text-icon" ></SvgIcon>
  </ACard>
</template>
<script>
import { enableStatusOptions, userGenderOptions } from '@/constants/business';
import {translateOptions} from "@/utils/common";
import {useFormRules} from "@/utils/form";
import SvgIcon from "@/components/custom/svg-icon/index.vue";

export default {
  name: 'UserSearch',
  components: {SvgIcon},
  props: {
    formModel: {
      type: Object,
      default: () => ({})
    }
  },
  data() {
    return {
      model: {
        username: '',
        userGender: '',
        nickName: '',
        userPhone: '',
        userEmail: '',
        status: ''
      },
      enableStatusOptions: enableStatusOptions,
      userGenderOptions: userGenderOptions
    }
  },
  watch: {
    model: {
      handler: function (val) {
        this.$emit('update:formModel', val);
      },
      immediate: true,
      deep: true
    }
  },
  computed: {
    rules(){
      const { patternRules } = useFormRules(); // inside computed to make locale reactive
      return {
        userEmail: patternRules.email,
        userPhone: patternRules.phone
      };
    }
  },
  methods: {
    reset() {
      this.$refs.formRef.resetFields();
      this.$emit('reset');
    },

    search() {
      this.$emit('search');
    },

    translateOptions(options){
      return translateOptions(options);
    }
  }
}


</script>
<style lang="scss" scoped>
.ant-advanced-search-form {
  padding: 24px;
  border: none;
  background: #ffffff;

  .ant-search-form-row{
    display: flex;
    flex-flow: row wrap;
  }

  .ant-form-item {
    margin-bottom: 4px;
  }

  .use-btn{
    margin-top: 12px;
  }
}
</style>
