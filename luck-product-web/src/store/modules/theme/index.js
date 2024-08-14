import {initThemeSettings} from "@/store/modules/theme/share";

/**
 * 主题状态管理
 */
const settings = initThemeSettings()

export default {
    namespaced: true,
    state: {
        ...settings
    },
    mutations: {
        TOGGLE_THEME_SCHEME(state) {
            const themeSchemes = ['light', 'dark', 'auto'];
            const index = themeSchemes.findIndex(item => item === state.themeScheme);
            const nextIndex = index === themeSchemes.length - 1 ? 0 : index + 1;
            state.themeScheme = themeSchemes[nextIndex];
        },
        RESET(state) {
            const initSettings = initThemeSettings();
            state = initSettings;
        }
    },
    actions: {
        toggleThemeScheme({ commit }){
            commit('TOGGLE_THEME_SCHEME');
        },
        reset({ commit }){
            commit('RESET');
        },
    }
};
