<template>
  <div>
    <ADivider class="text-base_text">{{ $t('theme.layoutMode.title') }}</ADivider>
    <LayoutModeCard :mode="theme.layout.mode" :disabled="app.isMobile" @update:mode="updateMode">
      <template #vertical>
        <div class="layout-sider h-full w-18px"></div>
        <div class="vertical-wrapper">
          <div class="layout-header"></div>
          <div class="layout-main"></div>
        </div>
      </template>
      <template #vertical-mix>
        <div class="layout-sider h-full w-8px"></div>
        <div class="layout-sider h-full w-16px"></div>
        <div class="vertical-wrapper">
          <div class="layout-header"></div>
          <div class="layout-main"></div>
        </div>
      </template>
      <template #horizontal>
        <div class="layout-header"></div>
        <div class="horizontal-wrapper">
          <div class="layout-main"></div>
        </div>
      </template>
      <template #horizontal-mix>
        <div class="layout-header"></div>
        <div class="horizontal-wrapper">
          <div class="layout-sider w-18px"></div>
          <div class="layout-main"></div>
        </div>
      </template>
    </LayoutModeCard>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import LayoutModeCard from "@/layout/base-layout/theme-drawer/components/layout-mode-card.vue";

export default {
  name: 'LayoutMode',
  components: {LayoutModeCard},
  props: {},
  data() {
    return {}
  },
  computed: {
    ...mapGetters(['theme', 'app']),
  },
  methods: {
      updateMode(value){
          this.$store.dispatch('theme/setLayoutMode',value);
      }
  }
}
</script>

<style scoped>
.layout-header {
    height: 16px;
    border-radius: 4px;
    --un-bg-opacity: 1;
    background-color: rgb(var(--primary-color) / var(--un-bg-opacity));
}

.layout-sider {
    border-radius: 4px;
    --un-bg-opacity: 1;
    background-color: rgb(var(--primary-300-color) / var(--un-bg-opacity));
}

.layout-main {
    flex: 1 1 0%;
    border-radius: 4px;
    --un-bg-opacity: 1;
    background-color: rgb(var(--primary-200-color) / var(--un-bg-opacity));
}

.vertical-wrapper {
    display: flex;
    flex-direction: column;
    flex: 1 1 0%;
    gap: 6px;
}

.horizontal-wrapper {
    display: flex;
    flex: 1 1 0%;
    gap: 6px;
}
</style>
