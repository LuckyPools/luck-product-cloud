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
        <div class="b">{{$t('system.title')}}</div>
      </div>
        <global-logo></global-logo>
        <global-breadcrumb></global-breadcrumb>
        <global-content></global-content>
        <global-footer></global-footer>
        <theme-button></theme-button>
        <user-avatar></user-avatar>
    </a-form>
  </div>
</template>

<script>
  import { login } from "@/api/auth";
  import GlobalLogo from "@/layout/base-layout/global-logo/index.vue";
  import GlobalBreadcrumb from "@/layout/base-layout/global-breadcrumb/index.vue";
  import GlobalContent from "@/layout/base-layout/global-content/index.vue";
  import GlobalFooter from "@/layout/base-layout/global-footer/index.vue";
  import ThemeButton from "@/layout/base-layout/global-header/components/theme-button.vue";
  import UserAvatar from "@/layout/base-layout/global-header/components/user-avatar.vue";
  export default {
    name: 'Login',
      components: {UserAvatar, ThemeButton, GlobalFooter, GlobalContent, GlobalBreadcrumb, GlobalLogo},
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
    methods:{
      login(){
        this.$store.dispatch(
            'user/setInfo',{name: '张三'});
        console.log(this.$store.state.user.info);
        login(this.form).then(res=>{
          console.log(res);
        })
      }
    }
  }
</script>
<style lang="scss">
  .a{
    .b{
      color: red;
    }
  }
</style>
