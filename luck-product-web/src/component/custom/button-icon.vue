<template>
  <ATooltip :placement="tooltipPlacement" :get-popup-container="getPopupContainer" :title="tooltipContent">
    <AButton type="text" v-bind="$attrs" :class="['h-36px','text-icon']">
      <div class="flex-center gap-8px">
        <slot>
          <SvgIcon :icon="icon" />
        </slot>
      </div>
    </AButton>
  </ATooltip>
</template>
<script>
// todo 引入taiwindmerge
import SvgIcon from "@/component/custom/svg-icon.vue";

export default {
    name: 'ButtonIcon',
    components: {SvgIcon},
    inheritAttrs: false,
    props:{
        icon:{
            type: String,
            default: ''
        },
        tooltipContent:{
            type: String,
            default: ''
        },
        tooltipPlacement:{
            type: String,
            default: 'bottom'
        },
        triggerParent:{
            type: Boolean,
            default: false
        },
    },
    data() {
        return {
        }
    },
    methods: {
        getPopupContainer(triggerNode) {
            return this.triggerParent ? triggerNode.parentElement : document.body;
        }
    }
}
</script>
<style scoped></style>
