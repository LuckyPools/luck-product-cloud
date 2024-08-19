import Vue from 'vue';
import Router from 'vue-router';
import { customRoutes } from './routes';

Vue.use(Router);

export default new Router({
    routes: customRoutes
})
