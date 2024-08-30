import {
    initThemeSettings,
    toggleCssDarkMode,
    toggleGrayscaleMode,
    setupThemeVarsToHtml
} from "@/store/modules/theme/utils";
import {getPaletteColorByNumber} from "@/store/modules/theme/palette";
import { usePreferredColorScheme } from '@vueuse/core';


function getThemeColors(state) {
    const {themeColor, otherColor, isInfoFollowPrimary} = state;
    return {
        primary: themeColor,
        ...otherColor,
        info: isInfoFollowPrimary ? themeColor : otherColor.info
    };
}


function initSetting (){
    const settings = initThemeSettings();
    setupThemeVarsToHtml(getThemeColors(settings));
    return settings;
}

const settings = initSetting();


export default {
    namespaced: true,
    state: {
        ...settings,
        osTheme: usePreferredColorScheme()
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
        SET_THEME_SCHEME(state, value) {
            state.themeScheme = value;
        },
        SET_LAYOUT_MODE(state, value){
            state.layout.mode = value;
        },
        SET_GRAYSCALE(state, value) {
            state.grayscale = value;
        },
        UPDATE_THEME_COLORS(state, colorData) {
            const {key, colorValue} = colorData;
            if (key === 'primary') {
                state.themeColor = colorValue;
            } else {
                state.otherColor[key] = colorValue;
            }
        }
    },
    actions: {
        toggleThemeScheme({commit, getters}) {
            commit('TOGGLE_THEME_SCHEME');
            toggleCssDarkMode(getters.darkMode);
        },
        reset({commit}) {
            commit('RESET');
        },
        setThemeScheme({commit, getters}, value) {
            commit('SET_THEME_SCHEME', value);
            toggleCssDarkMode(getters.darkMode);
        },
        setLayoutMode({commit}, value) {
            commit('SET_LAYOUT_MODE', value);
        },
        setGrayscale({commit}, value) {
            commit('SET_GRAYSCALE', value);
            toggleGrayscaleMode(value);
        },
        updateThemeColors({commit, state, getters}, colorData) {
            const {key, color} = colorData;
            let colorValue = color;
            if (state.recommendColor) {
                // get a color palette by provided color and color name, and use the suitable color
                colorValue = getPaletteColorByNumber(color, 500, true);
            }
            commit('UPDATE_THEME_COLORS', {key, colorValue});
            setupThemeVarsToHtml(getters.themeColors);
        },
        cacheThemeSettings({state}){
            localStorage.setItem('themeSettings', JSON.stringify(state));
        }
    },
    getters: {
        themeColors: state => {
            return getThemeColors(state);
        },
        darkMode: state => {
            if (state.themeScheme === 'auto') {
                return state.osTheme === 'dark';
            }
            return state.themeScheme === 'dark';
        },
        grayscaleMode: state => {
            return state.grayscale
        }
    }
};
