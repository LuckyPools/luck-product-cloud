import router from '@/router';
import {
    extractTabsByAllRoutes,
    filterTabsById,
    filterTabsByIds, getAllTabs, getDefaultHomeTab,
    getFixedTabIds,
    getTabByRoute,
    isTabInTabs, updateTabByI18nKey, updateTabsByI18nKey
} from "@/store/modules/tab/utils";


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
        SET_HOME_TAB(state, tab) {
            state.homeTab = tab;
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
            active = typeof active !== "undefined" ? active : true;
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
        },

        clearTabs({ state, commit, dispatch },excludes) {
            excludes = excludes ? excludes : [];
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

        initHomeTab({ commit, rootState}) {
            // todo 改为引用rootState.route.routeHome
            const homeTab = getDefaultHomeTab(router, 'home');
            commit('SET_HOME_TAB', homeTab);
        },

        isTabRetain({ state, commit }, tabId) {
            if (tabId === state.homeTab?.id) return true;
            const fixedTabIds = getFixedTabIds(state.tabs);
            return fixedTabIds.includes(tabId);
        },

        updateTabsByLocale({ state, commit }) {
            commit('SET_TABS', updateTabsByI18nKey(state.tabs));
            if (state.homeTab) {
                commit('SET_HOME_TAB', updateTabByI18nKey(state.homeTab))
            }
        }
    },
    getters: {
        allTabs: state => {
            return getAllTabs(state.tabs, state.homeTab);
        },
    }
};
