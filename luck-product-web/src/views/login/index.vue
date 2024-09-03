<template>
  <div>
    <a-form :form="form">
      <a-form-item label='姓名：'>
        <a-input v-model="form.username" placeholder="请输入客户名称"/>
      </a-form-item>
      <a-form-item label='密码：'>
        <a-input v-model="form.password" placeholder="请输入客户名称"/>
      </a-form-item>
      <a-form-item>
        <a-button v-on:click="login">登录</a-button>
      </a-form-item>
      <div class="a">
        <div class="b">{{ $t('system.title') }}</div>
      </div>
    </a-form>
  </div>
</template>

<script>
import {login} from "@/api/auth";
import {routerPushByKey} from "@/router";

export default {
  name: 'Login',
  data() {
    return {
      // 加载状态
      loading: false,
      // 表单数据
      form: {
        username: '',
        password: ''
      }
    };
  },
  methods: {
    login() {
      login(this.form).then(res => {
        routerPushByKey('home');
      }).catch(() => {
        this.$message.error("登录失败")
      })
    }
  }
}
</script>
<style lang="scss">
.a {
  .b {
    color: black;
  }
}
</style>
