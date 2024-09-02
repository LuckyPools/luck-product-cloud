<template>
  <ButtonIcon
      icon="majesticons:color-swatch-line"
      :tooltip-content="$t('icon.themeConfig')"
      trigger-parent
      @click.native="handleClick"
  />
</template>
<script>
import {mapGetters} from "vuex";
import ButtonIcon from "@/components/custom/button-icon/index.vue";

export default {
  name: 'ThemeButton',
  components: {ButtonIcon},
  data() {
    return {}
  },
  computed: {
    ...mapGetters(['app'])
  },
  methods: {
    handleClick() {
      this.$store.dispatch('app/openThemeDrawer');
    },
  }
}
</script>

<style scoped></style>
