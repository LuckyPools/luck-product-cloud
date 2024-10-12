import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import i18n from './locales';
import utils from './utils/common';
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';
import '@/styles/css/index.css';
import '@/styles/scss/index.scss';
import 'uno.css';

Vue.use(Antd);
Vue.prototype.$utils = utils

new Vue({
    el: '#app',
    router,
    store,
    i18n,
    render: h => h(App)
});

