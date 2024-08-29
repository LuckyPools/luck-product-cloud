import route from "@/store/modules/route";
import {getAllTabs} from "@/store/modules/tab/utils";

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

        getActiveFirstLevelMenuKey({ dispatch }) {
            const { hideInMenu, activeMenu } = route.meta;
            const name = route.name;
            const routeName = (hideInMenu ? activeMenu : name) || name;
            const [firstLevelRouteName] = routeName.split('_');
            dispatch('setActiveFirstLevelMenuKey',firstLevelRouteName)
        }
    },
    getters: {
        menus: (rootState,state) => {
            return rootState.user.menus.find(menu => menu.key === state.activeFirstLevelMenuKey)?.children || []
        },
    }
};
