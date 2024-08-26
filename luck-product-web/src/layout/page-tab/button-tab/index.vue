<template>
  <div
      class=":soy: relative inline-flex cursor-pointer items-center justify-center gap-12px whitespace-nowrap border-(1px solid) rounded-4px px-12px py-4px"
      :class="[
            'button-tab',
            { 'button-tab_dark': darkMode },
            { 'button-tab_active': active },
            { 'button-tab_active_dark': active && darkMode }
        ]"
  >
    <slot name="prefix"></slot>
    <slot></slot>
    <slot name="suffix"></slot>
  </div>
</template>
<script>
import {PageTabProps} from '../props.js'

export default {
  name: 'ButtonTab',
  props: {
    ...PageTabProps
  },
  data() {
    return {}
  },
  methods: {
    handleClick() {
      this.$emit('click');
    }
  }
}

</script>

<style scoped>
@import '../module.css';
</style>
