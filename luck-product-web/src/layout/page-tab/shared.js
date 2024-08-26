import {colord} from 'colord';

export const ACTIVE_COLOR = '#1890ff';

export function addColorAlpha(color, alpha) {
    return colord(color).alpha(alpha).toHex();
}


export function transformColorWithOpacity(color, alpha, bgColor) {
    const originColor = addColorAlpha(color, alpha);
    const {r: oR, g: oG, b: oB} = colord(originColor).toRgb();

    const {r: bgR, g: bgG, b: bgB} = colord(bgColor).toRgb();

    function calRgb(or, bg, al) {
        return bg + (or - bg) * al;
    }

    const resultRgb = {
        r: calRgb(oR, bgR, alpha),
        g: calRgb(oG, bgG, alpha),
        b: calRgb(oB, bgB, alpha)
    };

    return colord(resultRgb).toHex();
}

export function createCssVars(props) {
    const cssVars = {
        '--soy-primary-color': props.primaryColor,
        '--soy-primary-color1': props.primaryColor1,
        '--soy-primary-color2': props.primaryColor2,
        '--soy-primary-color-opacity1': props.primaryColorOpacity1,
        '--soy-primary-color-opacity2': props.primaryColorOpacity2,
        '--soy-primary-color-opacity3': props.primaryColorOpacity3
    };
    return cssVars;
}

export function createTabCssVars(primaryColor) {
    const cssProps = {
        primaryColor,
        primaryColor1: transformColorWithOpacity(primaryColor, 0.1, '#ffffff'),
        primaryColor2: transformColorWithOpacity(primaryColor, 0.3, '#000000'),
        primaryColorOpacity1: addColorAlpha(primaryColor, 0.1),
        primaryColorOpacity2: addColorAlpha(primaryColor, 0.15),
        primaryColorOpacity3: addColorAlpha(primaryColor, 0.3)
    };
    return createCssVars(cssProps);
}
