import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import i18n  from './i18n';
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import './style/global.css';
import 'uno.css';

Vue.use(Antd);

new Vue({
  el: '#app',
  router,
  store,
  i18n,
  render: h => h(App)
});

