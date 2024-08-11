/**
 * 主题状态管理
 */
const DEFAULT_STATE = Object.freeze({

});

export default {
    namespaced: true,
    state: {

    },
    mutations: {
        REMOVE_TAB(state, tabId) {
            const isRemoveActiveTab = activeTabId.value === tabId;
            const updatedTabs = filterTabsById(tabId, tabs.value);

            function update() {
                tabs.value = updatedTabs;
            }

            if (!isRemoveActiveTab) {
                update();
                return;
            }

            const activeTab = updatedTabs.at(-1) || homeTab.value;

            if (activeTab) {
                await switchRouteByTab(activeTab);
                update();
            }
        }
    },
    actions: {
        removeTab({ commit },tabId){
            commit('REMOVE_TAB',tabId);
        }
    }
};
