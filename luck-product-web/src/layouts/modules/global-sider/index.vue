<template>
  <DarkModeContainer class="size-full flex-col-stretch shadow-sider" :inverted="darkMenu">
    <GlobalLogo
        v-if="showLogo"
        :show-title="!app.siderCollapse"
        :style="{ height: theme.header.height + 'px' }"
    />
    <VerticalMixMenu v-if="isVerticalMix">
      <GlobalLogo :show-title="false" :style="{ height: theme.header.height + 'px' }"/>
    </VerticalMixMenu>
    <HorizontalMixMenu v-else-if="isHorizontalMix"/>
    <BaseMenu v-else :dark-theme="darkMenu" :menus="user.menus"/>
  </DarkModeContainer>
</template>
<script>
import {mapGetters} from "vuex";
import GlobalLogo from "@/layouts/modules/global-logo/index.vue";
import DarkModeContainer from "@/components/custom/dark-mode-container/index.vue";
import VerticalMixMenu from "@/layouts/modules/global-menu/vertical-mix-menu.vue";
import HorizontalMixMenu from "@/layouts/modules/global-menu/horizontal-mix-menu.vue";
import BaseMenu from "@/layouts/modules/global-menu/base-menu.vue";

export default {
  name: 'GlobalSider',
  components: {BaseMenu, HorizontalMixMenu, VerticalMixMenu, DarkModeContainer, GlobalLogo},
  props: {},
  data() {
    return {}
  },
  computed: {
    ...mapGetters(['theme', 'app', 'user']),
    ...mapGetters('theme', {
      darkMode: 'darkMode',
    }),
    isVerticalMix() {
      return this.theme.layout.mode === 'vertical-mix';
    },

    isHorizontalMix() {
      return this.theme.layout.mode === 'horizontal-mix';
    },

    darkMenu() {
      return !this.darkMode && !this.isHorizontalMix && this.theme.sider.inverted
    },

    showLogo() {
      return !this.isVerticalMix && !this.isHorizontalMix
    },
  },
  methods: {}
}
</script>

<style scoped></style>
