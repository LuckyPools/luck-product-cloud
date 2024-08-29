<template>
  <div class="h-full flex" @mouseleave="handleResetActiveMenu">
    <FirstLevelMenu :active-menu-key="activeFirstLevelMenuKey" :inverted="siderInverted" @select="handleSelectMixMenu">
      <slot></slot>
    </FirstLevelMenu>
    <div
        class="relative h-full transition-width-300"
        :style="{ width: app.mixSiderFixed && hasMenus ? theme.sider.mixChildMenuWidth + 'px' : '0px' }"
    >
      <DarkModeContainer
          class="absolute-lt h-full flex-col-stretch nowrap-hidden shadow-sm transition-all-300"
          :inverted="siderInverted"
          :style="{ width: showDrawer ? theme.sider.mixChildMenuWidth + 'px' : '0px' }"
      >
        <header class="flex-y-center justify-between px-12px" :style="{ height: theme.header.height + 'px' }">
          <h2 class="text-16px text-primary font-bold">{{ $t('system.title') }}</h2>
          <PinToggler
              :pin="app.mixSiderFixed"
              :class="{ 'text-white:88 !hover:text-white': siderInverted }"
              @click="toggleMixSiderFixed"
          />
        </header>
        <BaseMenu :dark-theme="siderInverted" :menus="user.menus"/>
      </DarkModeContainer>
    </div>
  </div>
</template>
<script>
import {mapGetters} from "vuex";
import FirstLevelMenu from "@/layout/base-layout/global-menu/first-level-menu.vue";
import DarkModeContainer from "@/component/common/dark-mode-container.vue";
import PinToggler from "@/component/common/pin-toggler.vue";
import BaseMenu from "@/layout/base-layout/global-menu/base-menu.vue";

export default {
  name: 'VerticalMixMenu',
  components: {BaseMenu, PinToggler, DarkModeContainer, FirstLevelMenu},
  data() {
    return {
      drawerVisible: false,
      // todo 分析
      activeFirstLevelMenuKey: 'to_test'
    }
  },
  computed: {
    ...mapGetters(['app', 'theme', 'user', 'route']),
    ...mapGetters('theme', {
      darkMode: 'darkMode',
    }),
    siderInverted() {
      return !this.darkMode && this.theme.sider.inverted
    },

    hasMenus() {
      return this.user.menus.length > 0
    },

    showDrawer() {
      return this.hasMenus && (this.drawerVisible || this.app.mixSiderFixed)
    }
  },
  methods: {

    setDrawerVisible(value) {
      this.drawerVisible = value;
    },

    handleSelectMixMenu(menu) {
      this.$store.dispatch('context/setActiveFirstLevelMenuKey',menu.key);
      // this.setActiveFirstLevelMenuKey(menu.key);
      if (menu.children?.length) {
        this.setDrawerVisible(true);
      } else {
        this.$router.push({name: menu.routeKey, query: {}})
      }
    },

    handleResetActiveMenu() {
      let that = this;
      this.$store.dispatch('context/getActiveFirstLevelMenuKey').then(() => {
        that.setDrawerVisible(false);
      })
    },

    toggleMixSiderFixed() {
      this.$store.dispatch('app/toggleMixSiderFixed');
    }
  }
}

</script>
<style scoped></style>
