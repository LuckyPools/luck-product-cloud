/**
 * 路由状态管理
 */
const DEFAULT_STATE = Object.freeze({

});

export default {
    namespaced: true,
    state: {
        breadcrumbs: [
            {icon: '',label: '测试',key: 'test',routeKey: 'to_test',
                children : [
                {icon: '',label: '子项1',key: 'item1',routeKey: 'to_item1'},
                {icon: '',label: '子项2',key: 'item2',routeKey: 'to_item2'}]
            },
            {icon: '',label: '测试1',key: 'test1',routeKey: 'to_test1'}
        ],
        cacheRoutes: [

        ]
    },
    getters: {

    },
    mutations: {

    },
    actions: {

    }
};
