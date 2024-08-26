import {$t} from '@/i18n';
import router from '@/router';
import {getRoutePath} from "@/router/routes";

/**
 * 主题状态管理
 */
export function filterTabsById(tabId, tabs) {
    return tabs.filter(tab => tab.id !== tabId);
}

export function filterTabsByIds(tabIds, tabs) {
    return tabs.filter(tab => !tabIds.includes(tab.id));
}

export function getFixedTabIds(tabs) {
    const fixedTabs = getFixedTabs(tabs);
    return fixedTabs.map(tab => tab.id);
}

export function getFixedTabs(tabs) {
    return tabs.filter(tab => tab.fixedIndex !== undefined);
}

export function isTabInTabs(tabId, tabs) {
    return tabs.some(tab => tab.id === tabId);
}

export function getTabIdByRoute(route) {
    const {path, query = {}, meta} = route;
    let id = path;
    if (meta.multiTab) {
        const queryKeys = Object.keys(query).sort();
        const qs = queryKeys.map(key => `${key}=${query[key]}`).join('&');

        id = `${path}?${qs}`;
    }
    return id;
}

export function getTabByRoute(route) {
    const {name, path, fullPath = path, meta} = route;
    const {title, i18nKey, fixedIndexInTab} = meta;
    // Get icon and localIcon from getRouteIcons function
    const {icon, localIcon} = getRouteIcons(route);

    const label = i18nKey ? $t(i18nKey) : title;

    const tab = {
        id: getTabIdByRoute(route),
        label,
        routeKey: name,
        routePath: path,
        fullPath,
        fixedIndex: fixedIndexInTab,
        icon,
        localIcon,
        i18nKey
    };

    return tab;
}

export function getRouteIcons(route) {
    // Set default value for icon at the beginning
    // todo VITE_MENU_ICON默认图标
    let icon = route?.meta?.icon || '';
    let localIcon = route?.meta?.localIcon;
    // Route.matched only appears when there are multiple matches,so check if route.matched exists
    if (route.matched) {
        // Find the meta of the current route from matched
        const currentRoute = route.matched.find(r => r.name === route.name);
        // If icon exists in currentRoute.meta, it will overwrite the default value
        icon = currentRoute?.meta?.icon || icon;
        localIcon = currentRoute?.meta?.localIcon;
    }
    return {icon, localIcon};
}

export function extractTabsByAllRoutes(router, tabs) {
    const routes = router.getRoutes();
    const routeNames = routes.map(route => route.name);
    return tabs.filter(tab => routeNames.includes(tab.routeKey));
}

export function updateTabsByI18nKey(tabs) {
    return tabs.map(tab => updateTabByI18nKey(tab));
}


export function updateTabByI18nKey(tab) {
    const {i18nKey, label} = tab;
    return {
        ...tab,
        label: i18nKey ? $t(i18nKey) : label
    };
}

/**
 * Get default home tab
 *
 * @param router
 * @param homeRouteName routeHome in useRouteStore
 */
export function getDefaultHomeTab(router, homeRouteName) {
    const homeRoutePath = getRoutePath(homeRouteName);
    const i18nLabel = $t(`route.${homeRouteName}`);
    let matchTab = router.matcher.match(homeRoutePath);
    let homeTab = {
        id: homeRoutePath,
        label: i18nLabel || homeRouteName,
        routeKey: homeRouteName,
        routePath: homeRoutePath,
        fullPath: homeRoutePath,
        i18nKey: matchTab?.meta?.i18nKey,
        icon: matchTab?.meta?.icon
    };
    return homeTab;
}

function findRouteByName(routes, name) {
    for (let route of routes) {
        // 检查当前路由的名称是否匹配
        if (route.name === name) {
            return route; // 找到匹配的路由，返回它
        }
        // 如果当前路由有子路由且未找到匹配的路由，则递归查找
        if (route.children && route.children.length > 0) {
            const foundRoute = findRouteByName(route.children, name);
            if (foundRoute) {
                return foundRoute; // 如果在子路由中找到了匹配的路由，返回它
            }
        }
    }
    // 如果没有找到匹配的路由，返回null
    return null;
}


/**
 * Get all tabs
 *
 * @param tabs Tabs
 * @param homeTab Home tab
 */
export function getAllTabs(tabs, homeTab) {
    if (!homeTab) {
        return [];
    }
    const filterHomeTabs = tabs.filter(tab => tab.id !== homeTab.id);

    const fixedTabs = filterHomeTabs.filter(isFixedTab).sort((a, b) => a.fixedIndex - b.fixedIndex);

    const remainTabs = filterHomeTabs.filter(tab => !isFixedTab(tab));

    const allTabs = [homeTab, ...fixedTabs, ...remainTabs];

    return updateTabsLabel(allTabs);
}


/**
 * Update tabs label
 *
 * @param tabs
 */
function updateTabsLabel(tabs) {
    const updated = tabs.map(tab => ({
        ...tab,
        label: tab.newLabel || tab.oldLabel || tab.label
    }));

    return updated;
}

/**
 * Is fixed tab
 *
 * @param tab
 */
function isFixedTab(tab) {
    return tab.fixedIndex !== undefined && tab.fixedIndex !== null;
}
