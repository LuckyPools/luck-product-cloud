<template>
  <div class="app-container">
    <div>
      <div>
        <ChromeTabBg></ChromeTabBg>
        <ChromeTab>
          <span slot="prefix">1111111111111111</span>
        </ChromeTab>
        <svg-close>
        </svg-close>
      </div>
    </div>
  </div>
</template>
<script>
import ChromeTabBg from "@/layout/page-tab/chrome-tab-bg/index.vue";
import ChromeTab from "@/layout/page-tab/chrome-tab/index.vue";
import SvgClose from "@/layout/page-tab/svg-close/index.vue";

export default {
  name: 'baseLayout',
  components: {SvgClose, ChromeTab, ChromeTabBg},
  data() {
    return {
    }
  }
}
</script>
