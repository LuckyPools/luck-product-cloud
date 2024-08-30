
/**
 * 上下文状态管理
 */
export default {
    namespaced: true,
    state: {
        activeFirstLevelMenuKey: '',
    },
    mutations: {
        SET_ACTIVE_FIRST_LEVEL_MENU_KEY(state, value){
            state.activeFirstLevelMenuKey = value;
        }
    },
    actions: {
        setActiveFirstLevelMenuKey({ commit }, value) {
            commit('SET_ACTIVE_FIRST_LEVEL_MENU_KEY', value)
        },

        getActiveFirstLevelMenuKey({ dispatch, rootState }) {
            const { hideInMenu, activeMenu } = rootState.route.curRoute.meta;
            const name = rootState.route.curRoute.name;
            const routeName = (hideInMenu ? activeMenu : name) || name;
            const [firstLevelRouteName] = routeName.split('_');
            dispatch('setActiveFirstLevelMenuKey',firstLevelRouteName)
        }
    },
    getters: {
        menus(state, getters, rootState){
            return rootState.user.menus.find(menu => menu.key === state.activeFirstLevelMenuKey)?.children || []
        },
    }
};
