<template>
  <ButtonIcon
    :key="String(collapsed)"
    :tooltip-content="collapsed ? $t('icon.expand') : $t('icon.collapse')"
    tooltip-placement="bottomLeft"
  >
    <SvgIcon :icon="icon" />
  </ButtonIcon>
</template>
<script>
import ButtonIcon from "@/component/custom/button-icon.vue";
import SvgIcon from "@/component/custom/svg-icon.vue";

export default {
  name: 'MenuToggler',
  components: {SvgIcon, ButtonIcon},
  props: {
    collapsed: {
      type: Boolean
    },
    arrowIcon: {
      type: Boolean
    },
  },
  computed:{
    icon(){
      const icons = {
        0: {
          0: 'line-md:menu-fold-left',
          1: 'line-md:menu-fold-right'
        },
        1: {
          0: 'ph-caret-double-left-bold',
          1: 'ph-caret-double-right-bold'
        }
      };
      const arrowIcon = Number(this.arrowIcon || false);
      const collapsed = Number(this.collapsed || false);
      return icons[arrowIcon][collapsed];
    }
  },
  data() {
    return {}
  },
  methods: {}
}
</script>

<style scoped></style>
