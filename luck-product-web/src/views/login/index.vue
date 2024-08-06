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
        <a-button>登录</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script>
  import { login } from "@/api/auth";
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
    methods:{
      login(){
        login(this.form).then(res=>{
          console.log(res);
        })
      }
    }
  }
</script>
