import Vue from 'vue';
import Router from 'vue-router';
import NProgress from 'nprogress';
import store from '../store'
import {createBuiltinVueRoutes} from './routes';

Vue.use(Router);

const router = new Router({
    routes: createBuiltinVueRoutes()
})

console.log(router)

// router/index.ts

NProgress.configure({
    easing: 'ease', // 动画方式
    speed: 500, // 递增进度条的速度
})

router.beforeEach((to, from, next) => {
    if (to.path.endsWith('/')) {
        next({path: to.path.slice(0, -1), replace: true})
    } else {
        // 进度条开始
        NProgress.start()
        next()
    }
})

router.afterEach((to, from, next) => {
    // 进度条结束
    NProgress.done()
    store.dispatch('route/setCurRoute', to);
})

export default router
