import router from '@/router';
import { $t } from '@/i18n';

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
    const { path, query = {}, meta } = route;
    let id = path;
    if (meta.multiTab) {
        const queryKeys = Object.keys(query).sort();
        const qs = queryKeys.map(key => `${key}=${query[key]}`).join('&');

        id = `${path}?${qs}`;
    }
    return id;
}

export function getTabByRoute(route) {
    const { name, path, fullPath = path, meta } = route;
    const { title, i18nKey, fixedIndexInTab } = meta;
    // Get icon and localIcon from getRouteIcons function
    const { icon, localIcon } = getRouteIcons(route);

    const label = i18nKey ? $t(i18nKey) : title;

    const tab= {
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
    return { icon, localIcon };
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
    const { i18nKey, label } = tab;
    return {
        ...tab,
        label: i18nKey ? $t(i18nKey) : label
    };
}

export default {
    namespaced: true,
    state: {
        activeTabId: '',
        homeTab: '',
        tabs: [],
    },
    mutations: {
        SET_TABS(state, tabs) {
            state.tabs = tabs;
        },
        SET_ACTIVE_TAB_ID(state, id) {
            state.activeTabId = id;
        },
        ADD_TAB(state, tab) {
            state.tabs.push(tab);
        }
    },
    actions: {
        addTab({ state, commit },route, active) {
            const tab = getTabByRoute(route);
            const isHomeTab = tab.id === state.homeTab?.id;
            if (!isHomeTab && !isTabInTabs(tab.id, state.tabs)) {
                commit('ADD_TAB',tab);
            }
            if (active) {
                commit('SET_ACTIVE_TAB_ID',tab.id);
            }
        },

        removeTab({ state, commit, dispatch },tabId){
            const isRemoveActiveTab = state.activeTabId === tabId;
            const updatedTabs = filterTabsById(tabId, state.tabs);
            function update() {
                commit('SET_TABS',updatedTabs)
            }
            if (!isRemoveActiveTab) {
                update();
                return;
            }
            const activeTab = updatedTabs.at(-1) || state.homeTab;
            if (activeTab) {
                dispatch('switchRouteByTab',activeTab).then(() => {
                    update();
                })
            }
            commit('REMOVE_TAB',tabId);
        },

        clearTabs({ state, commit, dispatch },excludes) {
            const remainTabIds = [...getFixedTabIds(state.tabs), ...excludes];
            const removedTabsIds = state.tabs.map(tab => tab.id).filter(id => !remainTabIds.includes(id));
            const isRemoveActiveTab = removedTabsIds.includes(state.activeTabId);
            const updatedTabs = filterTabsByIds(removedTabsIds, state.tabs);
            function update() {
                commit('SET_TABS',updatedTabs)
            }
            if (!isRemoveActiveTab) {
                update();
                return;
            }
            const activeTab = updatedTabs[updatedTabs.length - 1] || state.homeTab;
            dispatch('switchRouteByTab',activeTab).then(() => {
                update();
            })
        },

        clearLeftTabs({ state, commit, dispatch },tabId) {
            const tabIds = state.tabs.map(tab => tab.id);
            const index = tabIds.indexOf(tabId);
            if (index === -1) return;
            const excludes = tabIds.slice(index);
            dispatch('clearTabs',excludes);
        },

        clearRightTabs({ state, commit, dispatch },tabId) {
            const isHomeTab = tabId === state.homeTab?.id;
            if (isHomeTab) {
                dispatch('clearTabs',[]);
                return;
            }
            const tabIds = state.tabs.map(tab => tab.id);
            const index = tabIds.indexOf(tabId);
            if (index === -1) return;

            const excludes = tabIds.slice(0, index + 1);
            dispatch('clearTabs',excludes);
        },

        switchRouteByTab({ state, commit },tab) {
            const fail = router.push({path: tab.fullPath})
            if (!fail) {
                commit('SET_ACTIVE_TAB_ID',tab.id);
            }
        },

        initTabStore({ state, commit, rootState, dispatch },currentRoute) {
            const storageTabs = localStorage.getItem('globalTabs');
            if (rootState.theme.tab.cache && storageTabs) {
                const extractedTabs = extractTabsByAllRoutes(router, storageTabs);
                commit('SET_TABS',updateTabsByI18nKey(extractedTabs));
            }
            dispatch('addTab',currentRoute);
        },

        isTabRetain({ state, commit }, tabId) {
            if (tabId === state.homeTab?.id) return true;
            const fixedTabIds = getFixedTabIds(state.tabs);
            return fixedTabIds.includes(tabId);
        }
    }
};
