<template>
  <div style="width: 256px">
    <li role="menuitem" class="">
        <i aria-label="icon: pie-chart" class="anticon"><svg viewBox="64 64 896 896" data-icon="pie-chart" width="1em" height="1em" fill="currentColor" aria-hidden="true" focusable="false" class=""><path d="M864 518H506V160c0-4.4-3.6-8-8-8h-26a398.46 398.46 0 0 0-282.8 117.1 398.19 398.19 0 0 0-85.7 127.1A397.61 397.61 0 0 0 72 552a398.46 398.46 0 0 0 117.1 282.8c36.7 36.7 79.5 65.6 127.1 85.7A397.61 397.61 0 0 0 472 952a398.46 398.46 0 0 0 282.8-117.1c36.7-36.7 65.6-79.5 85.7-127.1A397.61 397.61 0 0 0 872 552v-26c0-4.4-3.6-8-8-8zM705.7 787.8A331.59 331.59 0 0 1 470.4 884c-88.1-.4-170.9-34.9-233.2-97.2C174.5 724.1 140 640.7 140 552c0-88.7 34.5-172.1 97.2-234.8 54.6-54.6 124.9-87.9 200.8-95.5V586h364.3c-7.7 76.3-41.3 147-96.6 201.8zM952 462.4l-2.6-28.2c-8.5-92.1-49.4-179-115.2-244.6A399.4 399.4 0 0 0 589 74.6L560.7 72c-4.7-.4-8.7 3.2-8.7 7.9V464c0 4.4 3.6 8 8 8l384-1c4.7 0 8.4-4 8-8.6zm-332.2-58.2V147.6a332.24 332.24 0 0 1 166.4 89.8c45.7 45.6 77 103.6 90 166.1l-256.4.7z"></path></svg></i>
        <span style="">首页</span>
    </li>

    <a-menu
        :default-selected-keys="['1']"
        :default-open-keys="['2']"
    >
      <template v-for="item in list">
        <a-menu-item v-if="!item.children" :key="item.key">
          <a-icon type="pie-chart" />
          <span>{{ item.title }}</span>
        </a-menu-item>
        <sub-menu v-else :key="item.key" :menu-info="item" />
      </template>
    </a-menu>
  </div>
</template>

<script>
import { Menu } from 'ant-design-vue';
const SubMenu = {
  template: `
      <a-sub-menu :key="menuInfo.key" v-bind="$props" v-on="$listeners">
        <span slot="title">
          <a-icon type="mail" /><span>{{ menuInfo.title }}</span>
        </span>
        <template v-for="item in menuInfo.children">
          <a-menu-item v-if="!item.children" :key="item.key">
            <a-icon type="pie-chart" />
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
};
export default {
  components: {
    'sub-menu': SubMenu,
  },
  data() {
    return {
      collapsed: false,
      list: [
        {
          "key": "home",
          "label": "首页",
          "i18nKey": "route.home",
          "routeKey": "home",
          "routePath": "/home",
          "icon": "mdi:monitor-dashboard",
          "title": "首页"
        },
        {
          "key": "fire",
          "label": "文档",
          "i18nKey": "route.document",
          "routeKey": "fire",
          "routePath": "/fire",
          "icon": "mdi:monitor-dashboard",
          "title": "文档",
          "children": [
            {
              "key": "test_page",
              "label": "角色管理",
              "i18nKey": "route.manage_role",
              "routeKey": "test_page",
              "routePath": "/test",
              "icon": "carbon:user-role",
              "title": "角色管理"
            },
            {
              "key": "login_page",
              "label": "菜单管理",
              "i18nKey": "route.manage_menu",
              "routeKey": "login_page",
              "routePath": "/login",
              "icon": "material-symbols:route",
              "title": "菜单管理"
            }
          ]
        }
      ]
    };
  },
  methods: {
    toggleCollapsed() {
      this.collapsed = !this.collapsed;
    },
  },
};
</script>
