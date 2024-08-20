import {setLocale} from "@/i18n";

/**
 * 主题状态管理
 */
export default {
    namespaced: true,
    state: {
        locale: 'zh',
        localeOptions:[
            {
                label: '中文',
                key: 'zh'
            },
            {
                label: 'English',
                key: 'en'
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
        SET_RELOAD_FLAG(state, value) {
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

        TOGGLE_FULL_CONTENT(state) {
            state.fullContent = !state.fullContent;
        },

        SET_SIDER_COLLAPSE(state, value) {
            state.siderCollapse = value;
        },

        TOGGLE_SIDER_COLLAPSE(state) {
            state.siderCollapse = !state.siderCollapse;
        },

        CHANGE_LOCALE(state,value) {
            state.locale = value;
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
            commit('SET_RELOAD_FLAG', value);
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

        toggleFullContent({ commit }) {
            commit('TOGGLE_FULL_CONTENT');
        },

        setSiderCollapse({ commit }, value) {
            commit('SET_SIDER_COLLAPSE', value);
        },

        toggleSiderCollapse({ commit }) {
            commit('TOGGLE_SIDER_COLLAPSE');
        },

        changeLocale({ dispatch, commit, rootState },lang) {
            commit('CHANGE_LOCALE',lang);
            localStorage.setItem('locale',lang);
            setLocale(lang);
            // todo updateDocumentTitleByLocale
            dispatch('user/updateGlobalMenusByLocale', null, { root: true })
            dispatch('tab/updateTabsByLocale', null, { root: true })
            // todo setDayjsLocale(locale.value);
        },

        setMixSiderFixed({ commit }, value) {
            commit('SET_MIX_SIDER_FIXED', value);
        },

        toggleMixSiderFixed({ commit }) {
            commit('TOGGLE_SIDER_COLLAPSE');
        },

        reloadPage({ state, commit, rootState },duration = 300) {
            commit('SET_RELOAD_FLAG', false);
            const d = rootState.theme.page.animate ? duration : 40;
            new Promise(resolve => {
                setTimeout(resolve, d);
                commit('SET_RELOAD_FLAG', true);
            }).then(r => {});
        }
    }
};
