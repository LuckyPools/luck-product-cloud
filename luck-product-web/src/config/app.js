
export const themeLayoutModeRecord = {
    vertical: 'theme.layoutMode.vertical',
    'vertical-mix': 'theme.layoutMode.vertical-mix',
    horizontal: 'theme.layoutMode.horizontal',
    'horizontal-mix': 'theme.layoutMode.horizontal-mix'
};


export const themeSchemaRecord = {
    light: 'theme.themeSchema.light',
    dark: 'theme.themeSchema.dark',
    auto: 'theme.themeSchema.auto'
};

export function transformRecordToOption(record) {
    return Object.entries(record).map(([value, label]) => ({
        value,
        label
    }));
}

export const themeScrollModeRecord = {
    wrapper: 'theme.scrollMode.wrapper',
    content: 'theme.scrollMode.content'
};

export const themeScrollModeOptions = transformRecordToOption(themeScrollModeRecord);

export const themeTabModeRecord= {
    chrome: 'theme.tab.mode.chrome',
    button: 'theme.tab.mode.button'
};

export const themeTabModeOptions = transformRecordToOption(themeTabModeRecord);

export const themePageAnimationModeRecord = {
    'fade-slide': 'theme.page.mode.fade-slide',
    fade: 'theme.page.mode.fade',
    'fade-bottom': 'theme.page.mode.fade-bottom',
    'fade-scale': 'theme.page.mode.fade-scale',
    'zoom-fade': 'theme.page.mode.zoom-fade',
    'zoom-out': 'theme.page.mode.zoom-out',
    none: 'theme.page.mode.none'
};

export const themePageAnimationModeOptions = transformRecordToOption(themePageAnimationModeRecord);
