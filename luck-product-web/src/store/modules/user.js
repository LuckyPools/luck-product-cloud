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
    menus: [],
    // 当前登录用户的权限
    permissions: [],
    // 当前登录用户的角色
    roles: []
  },
  mutations: {
    // 设置登录用户的信息
    setUserInfo(state, info) {
      state.info = info;
    },
    // 设置登录用户的菜单
    setMenus(state, menus) {
      state.menus = menus;
    },
    // 设置登录用户的权限
    setPermissions(state, permissions) {
      state.permissions = permissions;
    },
    // 设置登录用户的角色
    setRoles(state, roles) {
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
      commit('setUserInfo', value);
    },
    /**
     * 更新菜单数据
     */
    setMenus({ commit }, value) {
      commit('setMenus', value);
    }
  }
};
