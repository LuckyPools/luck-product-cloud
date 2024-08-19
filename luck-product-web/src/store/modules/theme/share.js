import { overrideThemeSettings, themeSettings } from "@/theme/settings";
import { getColorPalette } from "@/store/modules/theme/palette";
import { getRgbOfColor } from './colord';
import { themeVars } from "@/theme/vars";
import { toggleHtmlClass } from "@/utils/common";


const DARK_CLASS = 'dark';
const BUILD_TIME = '2024-08-14'

/** Init theme settings */
export function initThemeSettings() {
    // todo 引入环境变量
    // const isProd = import.meta.env?.PROD;

    // if it is development mode, the theme settings will not be cached, by update `themeSettings` in `src/theme/settings.ts` to update theme settings
    // if (!isProd) return themeSettings;

    // if it is production mode, the theme settings will be cached in localStorage
    // if want to update theme settings when publish new version, please update `overrideThemeSettings` in `src/theme/settings.ts`

    const settings = localStorage.getItem('themeSettings') || themeSettings;

    const isOverride = localStorage.getItem('overrideThemeFlag') === BUILD_TIME;

    if (!isOverride) {
        Object.assign(settings, overrideThemeSettings);
        localStorage.setItem('overrideThemeFlag', BUILD_TIME);
    }

    return settings;
}



/**
 * Create theme token
 *
 * @param colors Theme colors
 * @param [recommended=false] Use recommended color. Default is `false`
 */
export function createThemeToken(colors, recommended = false) {
    const paletteColors = createThemePaletteColors(colors, recommended);

    const themeTokens = {
        colors: {
            ...paletteColors,
            nprogress: paletteColors.primary,
            container: 'rgb(255, 255, 255)',
            layout: 'rgb(247, 250, 252)',
            inverted: 'rgb(0, 20, 40)',
            base_text: 'rgb(31, 31, 31)'
        },
        boxShadow: {
            header: '0 1px 2px rgb(0, 21, 41, 0.08)',
            sider: '2px 0 8px 0 rgb(29, 35, 41, 0.05)',
            tab: '0 1px 2px rgb(0, 21, 41, 0.08)'
        }
    };

    const darkThemeTokens = {
        colors: {
            ...themeTokens.colors,
            container: 'rgb(28, 28, 28)',
            layout: 'rgb(18, 18, 18)',
            base_text: 'rgb(224, 224, 224)'
        },
        boxShadow: {
            ...themeTokens.boxShadow
        }
    };

    return {
        themeTokens,
        darkThemeTokens
    };
}

/**
 * Create theme palette colors
 *
 * @param colors Theme colors
 * @param [recommended=false] Use recommended color. Default is `false`
 */
function createThemePaletteColors(colors, recommended = false) {
    const colorKeys = Object.keys(colors);
    const colorPaletteVar = {};

    colorKeys.forEach(key => {
        const colorMap = getColorPalette(colors[key], recommended);

        colorPaletteVar[key] = colorMap['500'];
        for (const number in colorMap) {
            const hex = colorMap[number];
            colorPaletteVar[`${key}-${number}`] = hex;
        }
    });

    return colorPaletteVar;
}

/**
 * Get css var by tokens
 *
 * @param tokens Theme base tokens
 */
function getCssVarByTokens(tokens) {
    const styles = [];

    function removeVarPrefix(value) {
        return value.replace('var(', '').replace(')', '');
    }

    function removeRgbPrefix(value) {
        return value.replace('rgb(', '').replace(')', '');
    }

    for (const [key, tokenValues] of Object.entries(themeVars)) {
        for (const [tokenKey, tokenValue] of Object.entries(tokenValues)) {
            let cssVarsKey = removeVarPrefix(tokenValue);
            let cssValue = tokens[key][tokenKey];

            if (key === 'colors') {
                cssVarsKey = removeRgbPrefix(cssVarsKey);
                const { r, g, b } = getRgbOfColor(cssValue);
                cssValue = `${r} ${g} ${b}`;
            }

            styles.push(`${cssVarsKey}: ${cssValue}`);
        }
    }

    const styleStr = styles.join(';');

    return styleStr;
}

/**
 * Add theme vars to html
 *
 * @param tokens
 */
export function addThemeVarsToHtml(tokens, darkTokens) {
    const cssVarStr = getCssVarByTokens(tokens);
    const darkCssVarStr = getCssVarByTokens(darkTokens);

    const css = `
    html {
      ${cssVarStr}
    }
  `;

    const darkCss = `
    html.${DARK_CLASS} {
      ${darkCssVarStr}
    }
  `;

    const styleId = 'theme-vars';

    const style = document.querySelector(`#${styleId}`) || document.createElement('style');

    style.id = styleId;

    style.textContent = css + darkCss;

    document.head.appendChild(style);
}

/**
 * Toggle css dark mode
 *
 * @param darkMode Is dark mode
 */
export function toggleCssDarkMode(darkMode = false) {
    const { add, remove } = toggleHtmlClass(DARK_CLASS);

    if (darkMode) {
        add();
    } else {
        remove();
    }
}

/**
 * Toggle grayscale mode
 *
 * @param grayscaleMode Is grayscale mode
 */
export function toggleGrayscaleMode(grayscaleMode = false) {
    const GRAYSCALE_CLASS = 'grayscale';

    const { add, remove } = toggleHtmlClass(GRAYSCALE_CLASS);

    if (grayscaleMode) {
        add();
    } else {
        remove();
    }
}


