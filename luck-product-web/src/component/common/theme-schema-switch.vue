<template>
  <ButtonIcon
    :icon="icon"
    :tooltip-content="tooltipContent"
    :tooltip-placement="tooltipPlacement"
    @click="handleSwitch"
  />
</template>
<script>

import ButtonIcon from "@/component/custom/button-icon.vue";

const icons = {
  light: 'material-symbols:sunny',
  dark: 'material-symbols:nightlight-rounded',
  auto: 'material-symbols:hdr-auto'
};
export default {
  name: 'ThemeSchemaSwitch',
  components: {ButtonIcon},
  props: {
    themeSchema: {
      /**
       * 'light' | 'dark' | 'auto'
       */
      type: String
    },
    showTooltip: {
      type: Boolean,
      default: true
    },
    tooltipPlacement: {
      type: String,
      default: 'bottom'
    }
  },
  data() {
    return {}
  },
  computed: {
    icon(){
      return icons[this.themeSchema]
    },
    tooltipContent(){
      if (!this.showTooltip) return '';
      return this.$t('icon.themeSchema');
    }
  },
  methods: {
    handleSwitch() {
      this.$emit('switch');
    }
  }
}
</script>
<style scoped></style>
