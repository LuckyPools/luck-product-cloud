<template>
  <div>
    <el-form>
      <el-form-item label="姓名">
        <el-input v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button v-on:click="login">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import { login } from "@/api/system";
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
