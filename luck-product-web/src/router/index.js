import Vue from 'vue';
import Router from 'vue-router';
import {createBuiltinVueRoutes, customRoutes} from './routes';

Vue.use(Router);

export default new Router({
    routes: createBuiltinVueRoutes()
})
