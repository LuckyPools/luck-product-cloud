/**
 * 主题状态管理
 */
const DEFAULT_STATE = Object.freeze({

});

export default {
    namespaced: true,
    state: {
        locale: 'zh',
        localeOptions:[
            {
                label: '中文',
                key: 'zh-CN'
            },
            {
                label: 'English',
                key: 'en-US'
            }
        ],
        isMobile: false,
        reloadFlag: true,
        fullContent: false,
        contentXScrollable: false,
        themeDrawerVisible: false,
        siderCollapse: false,
        mixSiderFixed: false,
    },
    getters: {

    },
    mutations: {
        setReloadFlag(state, value) {
            state.reloadFlag = value;
        },
        SET_CONTENT_X_SCROLLABLE(state, value) {
            state.contentXScrollable = value;
        },
        SET_THEME_DRAWER_VISIBLE(state, value) {
            state.themeDrawerVisible = value;
        },
        SET_FULL_CONTENT(state, value) {
            state.fullContent = value;
        },
        SET_SIDER_COLLAPSE(state, value) {
            state.siderCollapse = value;
        },
        TOGGLE_SIDER_COLLAPSE(state) {
            state.fullContent = !state.fullContent;
        },
        CHANGE_LOCALE(state,value) {
            state.locale = value;
            localStorage.setItem('locale',value);
        },
        SET_MIX_SIDER_FIXED(state, value) {
            state.mixSiderFixed = value;
        },
        TOGGLE_MIX_SIDER_FIXED(state) {
            state.mixSiderFixed = !state.mixSiderFixed;
        },
    },
    actions: {
        setReloadFlag({ commit }, value) {
            commit('setReloadFlag', value);
        },
        setContentXScrollable({ commit }, value) {
            commit('SET_CONTENT_X_SCROLLABLE', value);
        },
        openThemeDrawer({ commit }) {
            commit('SET_THEME_DRAWER_VISIBLE', true);
        },
        closeThemeDrawer({ commit }) {
            commit('SET_THEME_DRAWER_VISIBLE', false);
        },
        setFullContent({ commit }, value) {
            commit('SET_FULL_CONTENT', value);
        },
        setSiderCollapse({ commit }, value) {
            commit('SET_SIDER_COLLAPSE', value);
        },
        toggleSiderCollapse({ commit }) {
            commit('TOGGLE_SIDER_COLLAPSE');
        },
        changeLocale({ commit },lang) {
            commit('CHANGE_LOCALE',lang);
        },
        setMixSiderFixed({ commit }, value) {
            commit('SET_MIX_SIDER_FIXED', value);
        },
        toggleMixSiderFixed({ commit }) {
            commit('TOGGLE_SIDER_COLLAPSE');
        },
    }
};
