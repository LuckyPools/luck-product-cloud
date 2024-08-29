/**
 * vuex状态管理
 */
import Vue from 'vue';
import Vuex from 'vuex';
import getters from './getters';
import app from './modules/app';
import user from './modules/user';
import context from './modules/context';
import route from './modules/route';
import theme from './modules/theme';
import tab from './modules/tab';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {},
    mutations: {},
    actions: {},
    modules: {
        app,
        user,
        context,
        theme,
        route,
        tab
    },
    getters
});
