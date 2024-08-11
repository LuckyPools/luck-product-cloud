/**
 * 登录状态管理
 */
export default {
  namespaced: true,
  state: {
    // 是否登录
    isLogin: true,
    // 当前登录用户信息
    info: {
      name: ''
    },
    // 当前登录用户的菜单
    menus: [
      {icon: '',label: '测试',key: 'test',routeKey: 'to_test',
        children : [
          {icon: '',label: '子项1',key: 'item1',routeKey: 'to_item1'},
          {icon: '',label: '子项2',key: 'item2',routeKey: 'to_item2'}]
      },
      {icon: '',label: '测试1',key: 'test1',routeKey: 'to_test1'}
    ],
    // 当前登录用户的权限
    permissions: [],
    // 当前登录用户的角色
    roles: []
  },
  mutations: {
    // 设置登录用户的信息
    SET_USER_INFO(state, info) {
      state.info = info;
    },
    // 设置登录用户的菜单
    SET_MENUS(state, menus) {
      state.menus = menus;
    },
    // 设置登录用户的权限
    SET_PERMISSIONS(state, permissions) {
      state.permissions = permissions;
    },
    // 设置登录用户的角色
    SET_ROLES(state, roles) {
      state.roles = roles;
    }
  },
  actions: {
    /**
     * 请求用户信息、权限、角色、菜单
     */
    async fetchUserInfo({ commit }) {
    },
    /**
     * 更新用户信息
     */
    setInfo({ commit }, value) {
      commit('SET_USER_INFO', value);
    },
    /**
     * 更新菜单数据
     */
    setMenus({ commit }, value) {
      commit('SET_MENUS', value);
    }
  }
};
