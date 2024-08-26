<template>
  <component
      :is="activeTabComponent.component"
      :class="activeTabComponent.class"
      :style="cssVars"
      v-bind="bindProps"
      @mouseup="handleMouseup"
  >
    <template #prefix>
      <slot name="prefix"></slot>
    </template>
    <slot></slot>
    <template #suffix>
      <slot name="suffix">
        <SvgClose v-if="closable" :class="'svg-close'" @click="handleClose"/>
      </slot>
    </template>
  </component>
</template>
<script>
import ChromeTab from '@/layout/page-tab/chrome-tab/index.vue';
import ButtonTab from '@/layout/page-tab/button-tab/index.vue';
import SvgClose from "@/layout/page-tab/svg-close/index.vue";
import {ACTIVE_COLOR, createTabCssVars} from './shared';
import {PageTabProps} from './props';


const props = {
  ...PageTabProps,
  mode: {
    type: String,
    default: 'chrome'
  },
  commonClass: {
    type: String,
    default: 'transition-all-300'
  },
  activeColor: {
    type: String,
    default: ACTIVE_COLOR
  },
  closable: {
    type: Boolean,
    default: true
  }
}

export default {
  name: 'PageTab',
  components: {SvgClose, ChromeTab, ButtonTab},
  props: props,
  data() {
    return {}
  },
  computed: {
    activeTabComponent() {
      const {mode, chromeClass, buttonClass} = this;
      const tabComponentMap = {
        chrome: {
          component: 'ChromeTab',
          class: chromeClass
        },
        button: {
          component: 'ButtonTab',
          class: buttonClass
        }
      };
      return tabComponentMap[mode];
    },

    cssVars() {
      return createTabCssVars(this.activeColor);
    },

    bindProps() {
      const res = Object.entries(this.$props).filter(
          ([key]) => !['chromeClass', 'buttonClass'].includes(key)
      );
      return Object.fromEntries(res);
    }
  },
  methods: {
    handleClose() {
      this.$emit('close');
    },

    handleMouseup(e) {
      if (e.button === 1) {
        this.handleClose();
      }
    }
  }
}

</script>

<style scoped>
@import 'module.css';
</style>
