import Vue from 'vue';
import App from './App.vue';
import router from './router';
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';

Vue.use(Antd);

new Vue({
  el: '#app',
  router,
  render: h => h(App)
});

