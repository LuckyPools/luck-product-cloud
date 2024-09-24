<template>
  <div>
    <SimpleScrollbar class="menu-wrapper" :class="{ 'select-menu': !darkTheme }">
      <AMenu
          :mode="mode"
          :theme="menuTheme"
          :inline-collapsed="inlineCollapsed"
          :selected-keys="selectedKeys"
          :default-open-keys="openKeys"
          :inline-indent="18"
          class="size-full transition-300 border-0! text-base_text!"
          :class="{ 'bg-container!': !darkTheme, 'horizontal-menu': isHorizontal }"
          @click="handleClickMenu">
        <template v-for="item in menus">
          <a-menu-item v-if="!item.children" :key="item.key">
            <div :class="{'collapsed-menu-item' : inlineCollapsed}">
              <SvgIcon :icon="item.icon" class="anticon"></SvgIcon>
              <span>{{ item.title }}</span>
            </div>
          </a-menu-item>
          <sub-menu v-else :key="item.key" :menu-info="item"/>
        </template>
      </AMenu>
    </SimpleScrollbar>
  </div>
</template>
<script>
import SimpleScrollbar from "@/layouts/modules/page-tab/simple-scrollbar/index.vue";
import {mapGetters} from "vuex";
import {transformColorWithOpacity} from "@/layouts/modules/page-tab/share";

import { Menu } from 'ant-design-vue';
import SvgIcon from "@/components/custom/svg-icon/index.vue";
const SubMenu = {
  template: `
      <a-sub-menu :key="menuInfo.key" v-bind="$props" v-on="$listeners" class="use-sub-menu">
        <span slot="title">
          <SvgIcon :icon="menuInfo.icon" class="anticon"></SvgIcon>
          <span>{{ menuInfo.title }}</span>
        </span>
        <template v-for="item in menuInfo.children">
          <a-menu-item v-if="!item.children" :key="item.key" class="text-base_text!">
            <SvgIcon :icon="item.icon"></SvgIcon>
            <span>{{ item.title }}</span>
          </a-menu-item>
          <sub-menu v-else :key="item.key" :menu-info="item" />
        </template>
      </a-sub-menu>
    `,
  name: 'SubMenu',
  // must add isSubMenu: true
  isSubMenu: true,
  props: {
    ...Menu.SubMenu.props,
    // Cannot overlap with properties within Menu.SubMenu.props
    menuInfo: {
      type: Object,
      default: () => ({}),
    },
  },
  components: {SvgIcon}
};
export default {
  name: 'BaseMenu',
  components: {SvgIcon, SubMenu, SimpleScrollbar},
  props: {
    darkTheme: {
      type: Boolean
    },
    mode: {
      type: String,
      default: 'inline'
    },
    menus: {
      /**
       * to see App.Global.Menu
       */
      type: Array
    }
  },
  data() {
    return {
      routeInfo: null
    }
  },
  computed: {

    ...mapGetters(['app', 'theme', 'user', 'route']),
    ...mapGetters('theme', {
      darkMode: 'darkMode',
    }),

    menuTheme() {
      return (this.darkTheme ? 'dark' : 'light')
    },

    isHorizontal() {
      return this.mode === 'horizontal'
    },

    siderCollapse() {
      return this.theme.layout.mode === 'vertical' && this.app.siderCollapse
    },

    inlineCollapsed() {
      return (this.mode === 'inline' ? this.siderCollapse : undefined)
    },

    selectedKeys() {
      const route = this.routeInfo;
      if (route) {
        const {hideInMenu, activeMenu} = route.meta;
        const name = route.name;
        const routeName = (hideInMenu ? activeMenu : name) || name;
        if (routeName) {
          return [routeName];
        } else {
          return [];
        }
      }
      return [];
    },

    openKeys() {
      if (this.isHorizontal || this.inlineCollapsed) return [];
      const [selectedKey] = this.selectedKeys;
      if (!selectedKey) return [];
      return this.getSelectedMenuKeyPath(selectedKey);
    },

    headerHeight() {
      return `${this.theme.header.height}px`
    },

    selectedBgColor() {
      const darkMode = this.darkMode;
      const themeColor = this.theme.themeColor;
      const light = transformColorWithOpacity(themeColor, 0.1, '#ffffff');
      const dark = transformColorWithOpacity(themeColor, 0.3, '#000000');

      return darkMode ? dark : light;
    },
    selectTextColor(){
      return this.theme.themeColor;
    },
  },
  watch: {
    $route: {
      handler: function (val, oldVal) {
        this.routeInfo = val;
      },
      immediate: true,
      deep: true
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

    getSelectedMenuKeyPathByKey(selectedKey) {
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

    findMenuPath(targetKey, menu) {
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
      border-radius: 8px;
    }

    .ant-menu-item:hover {
      color: var(--text-color-rgb);
    }
  }

  :deep(.ant-menu-submenu-title) {
    width: calc(100% - 16px);
    margin-inline: 8px;

    .ant-menu-submenu-arrow::before {
      background: var(--text-color-rgb);
    }

    .ant-menu-submenu-arrow::after {
      background: var(--text-color-rgb);
    }
  }

  :deep(.ant-menu-submenu-title):hover {
    color: var(--text-color-rgb);
    background-color: rgba(0, 0, 0, 0.06);
    border-radius: 8px;
  }

  :deep(.ant-menu-inline-collapsed) {
    > .ant-menu-item {
      padding-inline: calc(50% - 14px);
      padding: 0 14px !important;
    }

    .ant-menu-item-icon {
      vertical-align: -0.25em;
    }

    .ant-menu-submenu-title {
      padding-inline: calc(50% - 18px);
      padding: 0 16px !important;
      text-align: center;
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

  :deep(.ant-menu-submenu) {
    .ant-menu {
      background-color: transparent;
    }
  }

}

.select-menu {
  :deep(.ant-menu-inline) {
    .ant-menu-item-selected {
      background-color: v-bind(selectedBgColor);
      color: v-bind(selectTextColor) !important;
    }

    .ant-menu-item-selected::after {
      opacity: 0;
    }
  }

  :deep(.ant-menu-submenu-selected){
    color: v-bind(selectTextColor);

    .ant-menu-submenu-arrow::before {
      background: v-bind(selectTextColor);
    }

    .ant-menu-submenu-arrow::after {
      background: v-bind(selectTextColor);
    }
  }

  :deep(.ant-menu-item-active) {
    background-color: rgba(0, 0, 0, 0.06);
  }
}

.horizontal-menu {
  line-height: v-bind(headerHeight);
}

.collapsed-menu-item{
  text-align: center;
}
</style>
