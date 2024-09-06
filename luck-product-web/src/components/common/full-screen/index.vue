<template>
  <ButtonIcon :key="String(full)" :tooltip-content="full ? $t('icon.fullscreenExit') : $t('icon.fullscreen')">
      <SvgIcon v-if="full" icon="fullscreen-exit"></SvgIcon>
      <SvgIcon v-else icon="fullscreen"></SvgIcon>
  </ButtonIcon>
</template>
<script>
import ButtonIcon from "@/components/custom/button-icon/index.vue";
import SvgIcon from "@/components/custom/svg-icon/index.vue";

export default {
  name: 'FullScreen',
  components: {SvgIcon, ButtonIcon},
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
