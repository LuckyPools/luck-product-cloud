<template>
  <DarkModeContainer class="h-full flex-center">
    <a href="https://github.com/honghuangdc/soybean-admin/blob/main/LICENSE" target="_blank"
       rel="noopener noreferrer">
      Copyright MIT © 2021 Soybean
    </a>
  </DarkModeContainer>
</template>
<script>

import DarkModeContainer from "@/component/common/dark-mode-container.vue";

export default {
  name: 'GlobalFooter',
  components: {DarkModeContainer},
  data() {
    return {}
  },
  methods: {}
}
</script>
<style scoped></style>
