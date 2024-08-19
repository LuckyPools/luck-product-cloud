<template>
  <div>
      <ADivider>{{ $t('theme.layoutMode.title') }}</ADivider>
      <LayoutModeCard v-model:mode="theme.layout.mode" :disabled="app.isMobile">
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

export default {
    name: 'LayoutMode',
    props: {},
    data() {
        return {}
    },
    computed: {
        ...mapGetters(['theme','app']),
    },
    methods: {}
}
</script>

<style scoped>
.layout-header {
  --uno: h-16px bg-primary rd-4px;
}

.layout-sider {
  --uno: bg-primary-300 rd-4px;
}

.layout-main {
  --uno: flex-1 bg-primary-200 rd-4px;
}

.vertical-wrapper {
  --uno: flex-1 flex-col gap-6px;
}

.horizontal-wrapper {
  --uno: flex-1 flex gap-6px;
}
</style>
