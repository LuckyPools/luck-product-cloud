import Vue from 'vue';
import Router from 'vue-router';
import NProgress from 'nprogress'
import {createBuiltinVueRoutes} from './routes';

Vue.use(Router);

const router =  new Router({
    routes: createBuiltinVueRoutes()
})

// router/index.ts

NProgress.configure({
    easing: 'ease', // 动画方式
    speed: 500, // 递增进度条的速度
})

router.beforeEach((to, from, next) => {
    // 进度条开始
    NProgress.start()
    next()
})

router.afterEach(() => {
    // 进度条结束
    NProgress.done()
})

export default router