<template>
  <FirstLevelMenu :active-menu-key="activeFirstLevelMenuKey" @select="handleSelectMixMenu">
    <slot></slot>
  </FirstLevelMenu>
</template>

<script>
import FirstLevelMenu from "@/layout/base-layout/global-menu/first-level-menu.vue";

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
      // todo 分析逻辑
      // setActiveFirstLevelMenuKey(menu.key);

      if (!menu.children?.length) {
        // todo 切换路由 routerPushByKey(key)
        this.$router.push({name: menu.routeKey, query: {}})
      }
    }
  }
}

</script>

<style scoped></style>
