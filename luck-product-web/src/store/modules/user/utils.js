import {$t} from "@/i18n";

/**
 * Get global menus by auth routes
 *
 * @param routes Auth routes
 */
export function getGlobalMenusByAuthRoutes(routes) {
    const menus = [];
    routes.forEach(route => {
        if (!route.meta?.hideInMenu) {
            const menu = getGlobalMenuByBaseRoute(route);

            if (route.children?.some(child => !child.meta?.hideInMenu)) {
                menu.children = getGlobalMenusByAuthRoutes(route.children);
            }

            menus.push(menu);
        }
    });

    return menus;
}

export function getGlobalMenuByBaseRoute(route) {
    const {name, path} = route;
    const {title, i18nKey, icon = import.meta.env.VITE_MENU_ICON, localIcon} = route.meta ?? {};

    const label = i18nKey ? $t(i18nKey) : title;

    return {
        key: name,
        label,
        i18nKey,
        routeKey: name,
        routePath: path,
        // todo 图标切换
        icon: icon,
        title: label
    };
}

export function updateLocaleOfGlobalMenus(menus) {
    const result = [];
    menus.forEach(menu => {
        const {i18nKey, label, children} = menu;
        const newLabel = i18nKey ? $t(i18nKey) : label;
        const newMenu = {
            ...menu,
            label: newLabel,
            title: newLabel
        };
        if (children?.length) {
            newMenu.children = updateLocaleOfGlobalMenus(children);
        }
        result.push(newMenu);
    });
    return result;
}
