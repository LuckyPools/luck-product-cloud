<template>
  <ADrawer
    :open="app.themeDrawerVisible"
    :title="$t('theme.themeDrawerTitle')"
    :closable="false"
    :body-style="{ padding: '0px' }"
    @close="handleClose"
  >
    <template #extra>
      <ButtonIcon icon="ant-design:close-outlined" class="h-28px" @click="handleClose" />
    </template>
    <SimpleScrollbar>
      <div class="px-24px pb-24px pt-8px">
        <DarkMode />
        <LayoutMode />
        <ThemeColor />
        <PageFun />
      </div>
    </SimpleScrollbar>
    <template #footer>
      <ConfigOperation />
    </template>
  </ADrawer>
</template>
<script>
import ButtonIcon from "@/component/custom/button-icon.vue";
import SimpleScrollbar from "@/layout/page-tab/simple-scrollbar/index.vue";
import DarkMode from "@/layout/base-layout/theme-drawer/modules/dark-mode.vue";
import LayoutMode from "@/layout/base-layout/theme-drawer/modules/layout-mode.vue";
import ThemeColor from "@/layout/base-layout/theme-drawer/modules/theme-color.vue";
import PageFun from "@/layout/base-layout/theme-drawer/modules/page-fun.vue";
import ConfigOperation from "@/layout/base-layout/theme-drawer/modules/config-operation.vue";
import {mapGetters} from "vuex";

export default {
    name: 'ThemeDrawer',
    components: {ConfigOperation, PageFun, ThemeColor, LayoutMode, DarkMode, SimpleScrollbar, ButtonIcon},
    data() {
        return {}
    },
    computed: {
        ...mapGetters(['app']),
    },
    methods: {
        handleClose(){
            this.$store.dispatch('app/closeThemeDrawer');
        }
    }
}
</script>

<style scoped></style>
