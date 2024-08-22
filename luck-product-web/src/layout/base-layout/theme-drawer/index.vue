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

    <div class="use-footer">
      <ConfigOperation />
    </div>
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

<style lang="scss" scoped>
/deep/  .ant-drawer-wrapper-body{
  display: flex;
  flex-direction: column;
  overflow: hidden;

  .ant-drawer-body{
    flex: 1;
    min-width: 0;
    min-height: 0;
    padding: 24px;
  }
}

.use-footer{
  z-index: 9999;
  position: absolute;
  bottom: 0;
  flex-shrink: 0;
  padding: 8px 16px;
  border-top: 1px solid rgba(5, 5, 5, 0.06);
}
</style>
