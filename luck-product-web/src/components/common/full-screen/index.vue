<template>
  <ButtonIcon :key="String(full)" :tooltip-content="full ? $t('icon.fullscreenExit') : $t('icon.fullscreen')">
      <a-icon v-if="full" type="fullscreen-exit"/>
      <a-icon v-else type="fullscreen"/>
  </ButtonIcon>
</template>
<script>
import ButtonIcon from "@/components/custom/button-icon/index.vue";

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
