import {overrideThemeSettings, themeSettings} from "@/theme/settings";

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
