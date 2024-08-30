<template>
  <FirstLevelMenu :active-menu-key="activeFirstLevelMenuKey" @select="handleSelectMixMenu">
    <slot></slot>
  </FirstLevelMenu>
</template>

<script>
import FirstLevelMenu from "@/layout/base-layout/global-menu/first-level-menu.vue";
import {routerPushByKey} from "@/router";

export default {
  name: 'HorizontalMixMenu',
  components: {FirstLevelMenu},
  props: {},
  data() {
    return {
      activeFirstLevelMenuKey: 'to_test'
    }
  },
  computed: {},
  methods: {

    handleSelectMixMenu(menu) {
      this.$store.dispatch('context/setActiveFirstLevelMenuKey',menu.key);
      if (!menu.children?.length) {
          routerPushByKey(menu.routeKey);
      }
    }
  }
}

</script>

<style scoped></style>
