<template>
  <div
      class=":soy: relative inline-flex cursor-pointer items-center justify-center gap-16px whitespace-nowrap px-24px py-6px -mr-18px"
      :class="[
      'chrome-tab',
      { 'chrome-tab_dark': darkMode },
      { 'chrome-tab_active': active },
      { 'chrome-tab_active_dark': active && darkMode }
    ]"
  >
    <div class=":soy: pointer-events-none absolute left-0 top-0 h-full w-full -z-1" :class="'chrome-tab__bg'">
      <ChromeTabBg/>
    </div>
    <slot name="prefix"></slot>
    <slot></slot>
    <slot name="suffix"></slot>
    <div class=":soy: absolute right-7px h-16px w-1px bg-#1f2225" :class="'chrome-tab-divider'"></div>
  </div>
</template>
<script>
import ChromeTabBg from "@/layout/page-tab/chrome-tab-bg/index.vue";
import {PageTabProps} from '../props.js'

export default {
  name: 'ChromeTab',
  components: {ChromeTabBg},
  props: {
    ...PageTabProps
  },
  data() {
    return {}
  }
}
</script>
<style scoped>
@import '../module.css';
</style>
