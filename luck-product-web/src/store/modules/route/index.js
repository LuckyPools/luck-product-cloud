import {getGlobalMenuByBaseRoute} from '../user/utils'


export function getBreadcrumbsByRoute(route, menus) {
    const key = route.name;
    const activeKey = route.meta?.activeMenu;

    const menuKey = activeKey || key;

    for (const menu of menus) {
        if (menu.key === menuKey) {
            const breadcrumb = menuKey !== activeKey ? menu : getGlobalMenuByBaseRoute(route);
            return [breadcrumb];
        }

        if (menu.children?.length) {
            const result = getBreadcrumbsByRoute(route, menu.children);
            if (result.length > 0) {
                return [menu, ...result];
            }
        }
    }

    return [];
}


/**
 * 路由状态管理
 */
export default {
    namespaced: true,
    state: {
        curRoute: null,
        routeHome: 'home',
        cacheRoutes: [],
        allCacheRoutes: []
    },
    mutations: {
        ADD_CACHE_ROUTES(state, routeKey) {
            state.cacheRoutes.push(routeKey);
        },
        SET_CUR_ROUTE(state, value) {
            state.curRoute = value;
        }
    },
    actions: {
        setCurRoute({state, commit}, value) {
            commit('SET_CUR_ROUTE', value)
        },

        reCacheRoutesByKey({state, commit, dispatch}, routeKey) {
            if (!state.allCacheRoutes.includes(routeKey)) {
                return;
            }
            dispatch('removeCacheRoutes', routeKey).then(() => {
                dispatch('app/reloadPage').then(() => {
                    dispatch('addCacheRoutes', routeKey);
                })
            })
        },

        addCacheRoutes({state, commit}, routeKey) {
            if (state.cacheRoutes.includes(routeKey)) return;
            commit('ADD_CACHE_ROUTES', routeKey)
        },

        removeCacheRoutes({state, commit}, routeKey) {
            const index = state.cacheRoutes.findIndex(item => item === routeKey);
            if (index === -1) return;
            state.cacheRoutes.splice(index, 1);
        },

        isCachedRoute({state, commit}, routeKey) {
            return state.allCacheRoutes.includes(routeKey);
        }
    },
    getters: {
        breadcrumbs: (state, getters, rootState) => {
            return getBreadcrumbsByRoute(state.curRoute, rootState.user.menus);
        }
    }
};
