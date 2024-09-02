import Vue from 'vue';
import Router from 'vue-router';
import NProgress from 'nprogress';
import store from '../store'
import {createBuiltinVueRoutes, whiteList, whiteRoutes} from './routes';
import {useTitle} from "@vueuse/core";
import {$t} from "@/locales";
import {getToken} from "@/utils/auth";

Vue.use(Router);

const router = new Router({
    routes: createBuiltinVueRoutes()
})

// router/index.ts

NProgress.configure({
    easing: 'ease', // 动画方式
    speed: 500, // 递增进度条的速度
})


router.beforeEach((to, from, next) => {
    if (to.path.endsWith('/')) {
        return next({path: to.path.slice(0, -1), replace: true})
    }
    // 进度条开始
    NProgress.start()
    // 判断是否登录
    if (getToken()) {
        // 还未注册动态路由则先获取
        if (!store.state.user.menus) {
            store
                .dispatch('user/fetchUserInfo')
                .then(({ menus, homePath }) => {
                    if (menus) {
                        store.dispatch('route/setHomeRouteKey', homePath);
                        // todo 添加动态路由
                        router.addRoutes([]);
                        next({ ...to});
                    }
                })
                .catch((e) => {
                    console.error(e);
                    next();
                });
        } else {
            next();
        }
    } else if (whiteList.includes(to.path)) {
        next();
    } else {
        next({
            path: '/login',
            query: to.path === '/' ? {} : { from: to.path }
        });
    }
})

router.afterEach((to, from, next) => {
    // 进度条结束
    NProgress.done()
    store.dispatch('route/setCurRoute', to).then(r => {});

    const { i18nKey, title } = to.meta;
    const documentTitle = i18nKey ? $t(i18nKey) : title;
    useTitle(documentTitle);
})


export function routerPushByKey(key, options) {
    const { query, params } = options || {};

    const routeLocation = {
        name: key
    };

    if (query) {
        routeLocation.query = query;
    }

    if (params) {
        routeLocation.params = params;
    }

    return router.push(routeLocation);
}

export default router
