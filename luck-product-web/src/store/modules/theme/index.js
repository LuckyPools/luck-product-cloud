import {addThemeVarsToHtml, createThemeToken, initThemeSettings} from "@/store/modules/theme/utils";
import {getPaletteColorByNumber} from "@/store/modules/theme/palette";


// todo 监听themeColors
function setupThemeVarsToHtml(themeColors) {
    const { themeTokens, darkThemeTokens } = createThemeToken(themeColors);
    addThemeVarsToHtml(themeTokens, darkThemeTokens);
}

function getThemeColors(state){
    const {themeColor, otherColor, isInfoFollowPrimary} = state;
    const colors = {
        primary: themeColor,
        ...otherColor,
        info: isInfoFollowPrimary ? themeColor : otherColor.info
    };
    return colors;
}

/**
 * 主题状态管理
 */
const settings = initThemeSettings();
/**
 * 设置style
 */
setupThemeVarsToHtml(getThemeColors(settings));



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
        },
        UPDATE_THEME_COLORS(state,key,colorValue){
            if (key === 'primary') {
                state.themeColor = colorValue;
            } else {
                state.otherColor[key] = colorValue;
            }
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
        updateThemeColors({ commit, state, getters },key, color) {
            let colorValue = color;
            if (state.recommendColor) {
                // get a color palette by provided color and color name, and use the suitable color
                colorValue = getPaletteColorByNumber(color, 500, true);
            }
            commit('UPDATE_THEME_COLORS',key, colorValue);
            setupThemeVarsToHtml(getters.themeColors);
        }
    },
    getters: {
        themeColors: state => {
            return getThemeColors(state);
        },
        darkMode: state => {
            if (state.themeScheme === 'auto') {
                // todo 研究逻辑
                // return state.osTheme === 'dark';
            }
            return state.themeScheme === 'dark';
        },
        grayscaleMode: state => {
            return state.grayscale
        }
    }
};
