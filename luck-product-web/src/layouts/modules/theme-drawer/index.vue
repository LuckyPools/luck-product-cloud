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
          <div class="text-16px font-bold text-base_text">{{ $t('theme.themeDrawerTitle') }}</div>
        </div>
        <ButtonIcon icon="ant-design:close-outlined" class="h-28px" @click.native="handleClose"/>
      </div>
    </template>

    <SimpleScrollbar>
      <div class="px-24px pb-24px pt-8px">
        <DarkMode/>
        <LayoutMode/>
        <ThemeColor/>
        <PageFun/>
      </div>
    </SimpleScrollbar>

    <div class="use-footer">
      <ConfigOperation/>
    </div>
  </ADrawer>
</template>
<script>
import ButtonIcon from "@/components/custom/button-icon/index.vue";
import SimpleScrollbar from "@/layouts/modules/page-tab/simple-scrollbar/index.vue";
import DarkMode from "@/layouts/modules/theme-drawer/modules/dark-mode/index.vue";
import LayoutMode from "@/layouts/modules/theme-drawer/modules/layout-mode/index.vue";
import ThemeColor from "@/layouts/modules/theme-drawer/modules/theme-color/index.vue";
import PageFun from "@/layouts/modules/theme-drawer/modules/page-fun/index.vue";
import ConfigOperation from "@/layouts/modules/theme-drawer/modules/config-operation/index.vue";
import { useEventListener } from '@vueuse/core';
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
  mounted(){
      let that = this;
      // cache theme settings when page is closed or refreshed
      useEventListener(window, 'beforeunload', () => {
          that.$store.dispatch('theme/cacheThemeSettings');
      });
  },
  methods: {
    handleClose() {
      this.$store.dispatch('app/closeThemeDrawer');
    }
  }
}
</script>

<style lang="scss" scoped>
:deep(.ant-drawer-wrapper-body) {
  display: flex;
  flex-direction: column;
  overflow: hidden;

  .ant-drawer-body {
    flex: 1;
    min-width: 0;
    min-height: 0;
    padding-bottom: 40px !important;
  }
}

.use-footer {
  z-index: 9999;
  position: absolute;
  width: 100%;
  bottom: 0;
  flex-shrink: 0;
  padding: 8px 16px;
  border-top: 1px solid rgba(5, 5, 5, 0.06);
}
</style>
