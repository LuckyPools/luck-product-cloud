
/**
 * 路由状态管理
 */
export default {
    namespaced: true,
    state: {
        routeHome: '',
        breadcrumbs: [],
        cacheRoutes: [],
        allCacheRoutes: []
    },
    mutations: {
        ADD_CACHE_ROUTES(state, routeKey) {
            state.cacheRoutes.push(routeKey);
        }
    },
    actions: {
        reCacheRoutesByKey({ state, commit, dispatch }, routeKey) {
            if(!state.allCacheRoutes.includes(routeKey)){
                return;
            }
            dispatch('removeCacheRoutes', routeKey).then(() => {
                dispatch('app/reloadPage').then(() => {
                    dispatch('addCacheRoutes',routeKey);
                })
            })
        },

        addCacheRoutes({ state, commit },routeKey) {
            if (state.cacheRoutes.includes(routeKey)) return;
            commit('ADD_CACHE_ROUTES',routeKey)
        },

        removeCacheRoutes({ state, commit },routeKey) {
            const index = state.cacheRoutes.findIndex(item => item === routeKey);
            if (index === -1) return;
            state.cacheRoutes.splice(index, 1);
        },

        isCachedRoute({ state, commit },routeKey) {
            return state.allCacheRoutes.includes(routeKey);
        }
    }
};
