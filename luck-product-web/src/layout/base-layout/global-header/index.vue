<template>
  <DarkModeContainer class="h-full flex-y-center px-12px shadow-header">
    <GlobalLogo v-if="showLogo" class="h-full" :style="{ width: theme.sider.width + 'px' }" />
    <HorizontalMenu v-if="showMenu" mode="horizontal" :menus="headerMenus" class="px-12px" />
    <div v-else class="h-full flex-y-center flex-1-hidden">
      <MenuToggler v-if="showMenuToggler" :collapsed="appStore.siderCollapse" @click="appStore.toggleSiderCollapse" />
      <GlobalBreadcrumb v-if="!appStore.isMobile" class="ml-12px" />
    </div>
    <div class="h-full flex-y-center justify-end">
      <FullScreen v-if="!appStore.isMobile" :full="isFullscreen" @click="handleClick" />
      <LangSwitch :lang="appStore.locale" :lang-options="appStore.localeOptions" @change-lang="appStore.changeLocale" />
      <ThemeSchemaSwitch
        :theme-schema="themeStore.themeScheme"
        :is-dark="themeStore.darkMode"
        @switch="themeStore.toggleThemeScheme"
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
import HorizontalMenu from '../global-menu/base-menu.vue';

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
    components:{GlobalBreadcrumb, GlobalLogo, UserAvatar, ThemeButton, DarkModeContainer},
    data() {
        return {
            isFullscreen: isFullscreen,
        }
    },
    computed: {
        ...mapGetters(['app','theme','user']),
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
        }
    }
}



</script>
<style scoped></style>
