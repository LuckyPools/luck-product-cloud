<template>
  <SimpleScrollbar class="menu-wrapper" :class="{ 'select-menu': !darkTheme }">
    <AMenu
      :mode="mode"
      :theme="menuTheme"
      :items="menus"
      :selected-keys="selectedKeys"
      :open-keys="openKeys"
      :inline-collapsed="inlineCollapsed"
      :inline-indent="18"
      class="size-full transition-300 border-0!"
      :class="{ 'bg-container!': !darkTheme, 'horizontal-menu': isHorizontal }"
      @click="handleClickMenu"
    />
  </SimpleScrollbar>
</template>
<script>
import SimpleScrollbar from "@/layout/page-tab/simple-scrollbar/index.vue";
import {mapGetters} from "vuex";
import {transformColorWithOpacity} from "@/layout/page-tab/shared";

export default {
  name: 'BaseMenu',
  components: {SimpleScrollbar},
  props:{
    darkTheme:{
      type: Boolean
    },
    mode:{
      type: String,
      default: 'inline'
    },
    menus:{
      /**
       * to see App.Global.Menu
       */
      type: Array
    }
  },
  data() {
    return {
    }
  },
  computed:{

    ...mapGetters(['app','theme','user','route']),

    menuTheme (){
      return (this.darkTheme ? 'dark' : 'light')
    },

    isHorizontal (){
      return this.mode === 'horizontal'
    },

    siderCollapse (){
      return this.theme.layout.mode === 'vertical' && appStore.siderCollapse
    },

    inlineCollapsed (){
      return (this.mode === 'inline' ? this.siderCollapse : undefined)
    },

    selectedKeys (){
      const route = this.$router.currentRoute;
      const { hideInMenu, activeMenu } = route.meta;
      const name = route.name;
      const routeName = (hideInMenu ? activeMenu : name) || name;
      if(routeName){
        return [routeName];
      }else{
        return [];
      }
    },

    openKeys (){
      if (this.isHorizontal || this.inlineCollapsed) return [];
      const [selectedKey] = this.selectedKeys;
      if (!selectedKey) return [];
      return this.getSelectedMenuKeyPath(selectedKey);
    },

    headerHeight (){
      return `${this.theme.header.height}px`
    },

    selectedBgColor (){
      const darkMode = this.theme.darkmode;
      const themeColor = this.theme.themeColor;
      const light = transformColorWithOpacity(themeColor, 0.1, '#ffffff');
      const dark = transformColorWithOpacity(themeColor, 0.3, '#000000');

      return darkMode ? dark : light;
    }
  },
  methods: {
    handleClickMenu(menuInfo) {
      const key = menuInfo.key;
      const query = this.getRouteQueryOfMetaByKey(key);
      this.$router.push({name: key, query: query});
    },

    getRouteQueryOfMetaByKey(key) {
      const meta = this.getRouteMetaByKey(key);
      const query = {};
      meta?.query?.forEach(item => {
        query[item.key] = item.value;
      });
      return query;
    },

    getRouteMetaByKey(key) {
      const allRoutes = this.$router.options.routes;
      return allRoutes.find(route => route.name === key)?.meta || null;
    },

    getSelectedMenuKeyPath(selectedKey) {
      return this.getSelectedMenuKeyPathByKey(selectedKey);
    },

    getSelectedMenuKeyPathByKey(selectedKey){
      const menus = this.user.menus;
      const keyPath = [];
      menus.some(menu => {
        const path = this.findMenuPath(selectedKey, menu);
        const find = Boolean(path?.length);
        if (find) {
          keyPath.push(...path);
        }
        return find;
      });
      return keyPath;
    },

    findMenuPath(targetKey, menu){
      const path = [];

      function dfs(item) {
        path.push(item.key);
        if (item.key === targetKey) {
          return true;
        }
        if (item.children) {
          for (const child of item.children) {
            if (dfs(child)) {
              return true;
            }
          }
        }
        path.pop();
        return false;
      }

      if (dfs(menu)) {
        return path;
      }

      return null;
    }
  }
}

</script>
<style lang="scss" scoped>
.menu-wrapper {
  :deep(.ant-menu-inline) {
    .ant-menu-item {
      width: calc(100% - 16px);
      margin-inline: 8px;
    }
  }

  :deep(.ant-menu-submenu-title) {
    width: calc(100% - 16px);
    margin-inline: 8px;
  }

  :deep(.ant-menu-inline-collapsed) {
    > .ant-menu-item {
      padding-inline: calc(50% - 14px);
    }

    .ant-menu-item-icon {
      vertical-align: -0.25em;
    }

    .ant-menu-submenu-title {
      padding-inline: calc(50% - 18px);
    }
  }

  :deep(.ant-menu-horizontal) {
    .ant-menu-item {
      display: flex;
      align-items: center;
    }

    .ant-menu-submenu-title {
      display: flex;
      align-items: center;
    }
  }
}

.select-menu {
  :deep(.ant-menu-inline) {
    .ant-menu-item-selected {
      background-color: v-bind(selectedBgColor);
    }
  }
}

.horizontal-menu {
  line-height: v-bind(headerHeight);
}
</style>
