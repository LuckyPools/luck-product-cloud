<template>
  <ADrawer
    :visible="app.themeDrawerVisible"
    :closable="false"
    :body-style="{ padding: '0px' }"
    :width="378"
    @close="handleClose"
  >
    <template #title>
      <div class="flex items-center">
        <div class="flex-1">
          <div class="text-16px font-bold">{{$t('theme.themeDrawerTitle')}}</div>
        </div>
        <ButtonIcon icon="ant-design:close-outlined" class="h-28px" @click.native="handleClose" />
      </div>
    </template>

    <SimpleScrollbar>
      <div class="px-24px pb-24px pt-8px">
        <DarkMode />
        <LayoutMode />
        <ThemeColor />
        <PageFun />
      </div>
    </SimpleScrollbar>
    <ConfigOperation />
    <template #footer>

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
