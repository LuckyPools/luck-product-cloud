
export declare const colord: (input: AnyColor | Colord) => Colord;


export function addColorAlpha(color, alpha) {
    return colord(color).alpha(alpha).toHex();
}


export function transformColorWithOpacity(color, alpha, bgColor) {
    const originColor = addColorAlpha(color, alpha);
    const { r: oR, g: oG, b: oB } = colord(originColor).toRgb();

    const { r: bgR, g: bgG, b: bgB } = colord(bgColor).toRgb();

    function calRgb(or: number, bg: number, al: number) {
        return bg + (or - bg) * al;
    }

    const resultRgb: RgbColor = {
        r: calRgb(oR, bgR, alpha),
        g: calRgb(oG, bgG, alpha),
        b: calRgb(oB, bgB, alpha)
    };

    return colord(resultRgb).toHex();
}

export function createTabCssVars(primaryColor: string) {
    const cssProps: PageTabCssVarsProps = {
        primaryColor,
        primaryColor1: transformColorWithOpacity(primaryColor, 0.1, '#ffffff'),
        primaryColor2: transformColorWithOpacity(primaryColor, 0.3, '#000000'),
        primaryColorOpacity1: addColorAlpha(primaryColor, 0.1),
        primaryColorOpacity2: addColorAlpha(primaryColor, 0.15),
        primaryColorOpacity3: addColorAlpha(primaryColor, 0.3)
    };

    return createCssVars(cssProps);
}
