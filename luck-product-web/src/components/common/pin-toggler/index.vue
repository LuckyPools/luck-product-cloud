<template>
  <ButtonIcon :tooltip-content="pin ? $t('icon.unpin') : $t('icon.pin')" tooltip-placement="bottomLeft" trigger-parent>
    <SvgIcon :icon="icon"/>
  </ButtonIcon>
</template>
<script>
import ButtonIcon from "@/components/custom/button-icon/index.vue";
import SvgIcon from "@/components/custom/svg-icon/index.vue";

export default {
  name: 'PinToggler',
  components: {SvgIcon, ButtonIcon},
  props: {
    pin: {
      type: Boolean
    }
  },
  data() {
    return {}
  },
  computed: {
    icon() {
      return this.pin ? 'mdi-pin-off' : 'mdi-pin'
    }
  },
  methods: {}
}
</script>
<style scoped></style>
