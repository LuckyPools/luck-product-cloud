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
        },
        SET_THEME_SCHEME(state,value) {
            state.themeScheme = value;
        },
        SET_GRAYSCALE(state,value) {
            state.grayscale = value;
        }
    },
    actions: {
        toggleThemeScheme({ commit }){
            commit('TOGGLE_THEME_SCHEME');
        },
        reset({ commit }){
            commit('RESET');
        },
        setThemeScheme({ commit },value) {
            commit('SET_THEME_SCHEME',value);
        },
        setGrayscale({ commit },value) {
            commit('SET_GRAYSCALE',value);
        },
        updateThemeColors({ commit, state },key, color) {
            let colorValue = color;

            if (settings.value.recommendColor) {
                // get a color palette by provided color and color name, and use the suitable color

                colorValue = getPaletteColorByNumber(color, 500, true);
            }

            if (key === 'primary') {
                settings.value.themeColor = colorValue;
            } else {
                settings.value.otherColor[key] = colorValue;
            }
        }
    }
};
