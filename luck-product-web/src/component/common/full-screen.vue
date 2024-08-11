<template>
  <ButtonIcon :key="String(full)" :tooltip-content="full ? $t('icon.fullscreenExit') : $t('icon.fullscreen')">
    <!-- todo 图标 -->
    <div v-if="full">fullscreen-exit</div>
    <div v-else>fullscreen</div>
  </ButtonIcon>
</template>
<script>
import ButtonIcon from "@/component/custom/button-icon.vue";

export default {
  name: 'FullScreen',
  components: {ButtonIcon},
  props: {
    full: {
      type: Boolean
    }
  },
  data() {
    return {}
  },
  methods: {}
}
</script>

<style scoped></style>
