<template>
  <DarkModeContainer class="h-full flex-y-center px-12px shadow-header">
    <GlobalLogo v-if="showLogo" class="h-full" :style="{ width: theme.sider.width + 'px' }" />
    <BaseMenu v-if="showMenu" mode="horizontal" :menus="headerMenus" class="px-12px" />
    <div v-else class="h-full flex-y-center flex-1-hidden">
      <MenuToggler v-if="showMenuToggler" :collapsed="app.siderCollapse" @click="handleToggle" />
      <GlobalBreadcrumb v-if="!app.isMobile" class="ml-12px" />
    </div>
    <div class="h-full flex-y-center justify-end">
      <FullScreen v-if="!app.isMobile" :full="isFullscreen" @click.native="handleClick" />
      <LangSwitch :lang="app.locale" :lang-options="app.localeOptions" @changeLang="changeLocale" />
      <ThemeSchemaSwitch
        :theme-schema="theme.themeScheme"
        :is-dark="darkMode"
        @switch="toggleThemeScheme"
      />
      <ThemeButton />
      <UserAvatar />
    </div>
  </DarkModeContainer>
</template>
<script>
import { useFullscreen } from '@vueuse/core';
import {mapGetters} from "vuex";
import DarkModeContainer from "@/component/common/dark-mode-container.vue";
import ThemeButton from "@/layout/base-layout/global-header/components/theme-button.vue";
import UserAvatar from "@/layout/base-layout/global-header/components/user-avatar.vue";
import GlobalLogo from "@/layout/base-layout/global-logo/index.vue";
import GlobalBreadcrumb from "@/layout/base-layout/global-breadcrumb/index.vue";
import MenuToggler from "@/component/common/menu-toggler.vue";
import FullScreen from "@/component/common/full-screen.vue";
import LangSwitch from "@/component/common/lang-switch.vue";
import ThemeSchemaSwitch from "@/component/common/theme-schema-switch.vue";
import BaseMenu from "@/layout/base-layout/global-menu/base-menu.vue";

const { isFullscreen, toggle } = useFullscreen();

export default {
    name: 'GlobalHeader',
    props:{
        showLogo:{
            type: Boolean
        },
        showMenuToggler:{
            type: Boolean
        },
        showMenu:{
            type: Boolean
        },
    },
    components:{
      BaseMenu,
      ThemeSchemaSwitch,
      LangSwitch,
      FullScreen, MenuToggler, GlobalBreadcrumb, GlobalLogo, UserAvatar, ThemeButton, DarkModeContainer},
    data() {
        return {
            isFullscreen: isFullscreen,
        }
    },
    computed: {
        ...mapGetters(['app','theme','user']),
        ...mapGetters('theme', {
            darkMode: 'darkMode',
        }),
        headerMenus(){
            // todo 混合菜单
            if (this.theme.layout.mode === 'horizontal') {
                return this.user.menus;
            }
            return [];
        }
    },
    methods: {
        handleClick(){
            toggle();
        },
        handleToggle(){
          this.$store.dispatch('app/toggleSiderCollapse');
        },
        changeLocale(lang) {
          this.$store.dispatch('app/changeLocale',lang);
        },
        toggleThemeScheme(){
          this.$store.dispatch('theme/toggleThemeScheme');
        }
    }
}

</script>
<style scoped></style>
