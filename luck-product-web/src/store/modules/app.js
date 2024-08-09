/**
 * 主题状态管理
 */
const DEFAULT_STATE = Object.freeze({

});

export default {
    namespaced: true,
    state: {
        reloadFlag: true,
        fullContent: false,
        contentXScrollable: false,
        themeDrawerVisible: false,
        siderCollapse: false
    },
    getters: {

    },
    mutations: {
        setReloadFlag(state, value) {
            state.reloadFlag = value;
        },
        setContentXScrollable(state, value) {
            state.contentXScrollable = value;
        },
        setThemeDrawerVisible(state, value) {
            state.themeDrawerVisible = value;
        },
    },
    actions: {
        setReloadFlag({ commit }, value) {
            commit('setReloadFlag', value);
        },
        setContentXScrollable({ commit }, value) {
            commit('setContentXScrollable', value);
        },
        openThemeDrawer({ commit }, value) {
            commit('setThemeDrawerVisible', true);
        },
        closeThemeDrawer({ commit }, value) {
            commit('setThemeDrawerVisible', false);
        },
    }
};
