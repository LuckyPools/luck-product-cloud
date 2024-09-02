<template>
  <DarkModeContainer class="h-full flex-y-center px-12px shadow-header">
    <GlobalLogo v-if="showLogo" class="h-full" :style="{ width: theme.sider.width + 'px' }"/>
    <BaseMenu v-if="showMenu" mode="horizontal" :menus="headerMenus" class="px-12px"/>
    <div v-else class="h-full flex-y-center flex-1-hidden">
      <MenuToggler v-if="showMenuToggler" :collapsed="app.siderCollapse" @click.native="handleToggle"/>
      <GlobalBreadcrumb v-if="!app.isMobile" class="ml-12px"/>
    </div>
    <div class="h-full flex-y-center justify-end">
      <FullScreen v-if="!app.isMobile" :full="isFullscreen" @click.native="handleClick"/>
      <LangSwitch :lang="app.locale" :lang-options="app.localeOptions" @changeLang="changeLocale"/>
      <ThemeSchemaSwitch
          :theme-schema="theme.themeScheme"
          :is-dark="darkMode"
          @switch="toggleThemeScheme"
      />
      <ThemeButton/>
      <UserAvatar/>
    </div>
  </DarkModeContainer>
</template>
<script>
import {useFullscreen} from '@vueuse/core';
import {mapGetters} from "vuex";
import DarkModeContainer from "@/components/common/dark-mode-container/index.vue";
import ThemeButton from "@/layouts/modules/global-header/components/theme-button.vue";
import UserAvatar from "@/layouts/modules/global-header/components/user-avatar.vue";
import GlobalLogo from "@/layouts/modules/global-logo/index.vue";
import GlobalBreadcrumb from "@/layouts/modules/global-breadcrumb/index.vue";
import MenuToggler from "@/components/common/menu-toggler/index.vue";
import FullScreen from "@/components/common/full-screen/index.vue";
import LangSwitch from "@/components/common/lang-switch/index.vue";
import ThemeSchemaSwitch from "@/components/common/theme-schema-switch/index.vue";
import BaseMenu from "@/layouts/modules/global-menu/base-menu.vue";

const {isFullscreen, toggle} = useFullscreen();

export default {
  name: 'GlobalHeader',
  props: {
    showLogo: {
      type: Boolean
    },
    showMenuToggler: {
      type: Boolean
    },
    showMenu: {
      type: Boolean
    },
  },
  components: {
    BaseMenu,
    ThemeSchemaSwitch,
    LangSwitch,
    FullScreen, MenuToggler, GlobalBreadcrumb, GlobalLogo, UserAvatar, ThemeButton, DarkModeContainer
  },
  data() {
    return {
      isFullscreen: isFullscreen,
    }
  },
  computed: {
    ...mapGetters(['app', 'theme', 'user']),
    ...mapGetters('theme', {
      darkMode: 'darkMode',
    }),
    ...mapGetters('context', {
        menus: 'menus',
    }),
    headerMenus() {
      if (this.theme.layout.mode === 'horizontal') {
        return this.user.menus;
      }
      if (this.theme.layout.mode === 'horizontal-mix') {
          return this.menus;
      }
      return [];
    }
  },
  methods: {
    handleClick() {
      toggle();
    },
    handleToggle() {
      this.$store.dispatch('app/toggleSiderCollapse');
    },
    changeLocale(lang) {
      this.$store.dispatch('app/changeLocale', lang);
    },
    toggleThemeScheme() {
      this.$store.dispatch('theme/toggleThemeScheme');
    }
  }
}

</script>
<style scoped></style>
