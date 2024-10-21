<template>
  <div style="width: 256px">
    <AProSelect v-model="eyeColor" :options="colors" size="default" label-key="label" value-key="name" width="120px" >
    </AProSelect>

    <AProSelect v-model="toYes" dict-code="D_SYS_YES_NO" size="default" label-key="name" value-key="code" width="120px" >
    </AProSelect>

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
import AProSelect from "@/components/extend/a-pro-select/index.vue";
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
      AProSelect
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
      ],
      mouseColor: 'blue',
      toYes: 'yes',
      eyeColor: 'red',
      colors: [
          {
              label: '红色',
              name: 'red',
              key: 'r'
          },
          {
              label: '蓝色',
              name: 'blue',
              key: 'b'
          },
          {
              label: '绿色',
              name: 'green',
              key: 'g'
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
