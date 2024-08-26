<template>
  <ATooltip :placement="tooltipPlacement" :get-popup-container="getPopupContainer" :title="tooltipContent">
    <!--    <AButton type="link" v-bind="$attrs" :class="['h-36px','text-icon']">-->
    <!--      <div class="flex-center gap-8px">-->
    <!--        <slot>-->
    <!--          <SvgIcon :icon="icon" />-->
    <!--        </slot>-->
    <!--  -->
    <!--      </div>-->
    <!--    </AButton>-->
    <button class="luck-btn ant-btn border-none shadow-none h-8 text-4 text-base_text" type="text">
      <div class="flex-center gap-8px">
        <slot>
          <SvgIcon :icon="icon"/>
        </slot>
      </div>
    </button>
  </ATooltip>
</template>
<script>
// todo 引入taiwindmerge
import SvgIcon from "@/component/custom/svg-icon.vue";

export default {
  name: 'ButtonIcon',
  components: {SvgIcon},
  inheritAttrs: false,
  props: {
    icon: {
      type: String,
      default: ''
    },
    tooltipContent: {
      type: String,
      default: ''
    },
    tooltipPlacement: {
      type: String,
      default: 'bottom'
    },
    triggerParent: {
      type: Boolean,
      default: false
    },
  },
  data() {
    return {}
  },
  methods: {
    getPopupContainer(triggerNode) {
      return this.triggerParent ? triggerNode.parentElement : document.body;
    }
  }
}
</script>
<style lang="scss" scoped>
.luck-btn {
  background-color: transparent;

  &:hover {
    border: none;

    background-color: rgba(0, 0, 0, 0.06);
  }
}
</style>
