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
        <SvgClose v-if="closable" :class="[style['svg-close']]" @click="handleClose" />
      </slot>
    </template>
  </component>
</template>
<script>
import { computed } from 'vue';
import type { Component } from 'vue';
import type { PageTabMode, PageTabProps } from '../../types';
import { ACTIVE_COLOR, createTabCssVars } from './shared';
import style from './index.module.css';

import ChromeTab from '@/layout/page-tab/chrome-tab/index.vue';
import ButtonTab from '@/layout/page-tab/button-tab/index.vue';
import { PageTabProps } from './chrome-tab/prop'
import SvgClose from "@/layout/page-tab/svg-close/index.vue";

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
    default: '#1890ff'
  },
  closable: {
    type: Boolean,
    default: true
  }
}


export default {
  name: 'PageTab',
  components: {SvgClose},
  props: props,
  data() {
    return {
    }
  },
  computed: {
    activeTabComponent() {
      const { mode, chromeClass, buttonClass } = props;
      const tabComponentMap = {
        chrome: {
          component: ChromeTab,
          class: chromeClass
        },
        button: {
          component: ButtonTab,
          class: buttonClass
        }
      };
      return tabComponentMap[mode];
    },

    cssVars(){
      return createTabCssVars(props.activeColor);
    }
  },
  methods: {
    handleClose() {
      this.$emit('close');
    },

    handleMouseup(e) {
      // close tab by mouse wheel button click
      if (e.button === 1) {
        this.handleClose();
      }
    }
  }
}



const cssVars = computed(() => createTabCssVars(props.activeColor));

const bindProps = computed(() => {
  const { chromeClass: _chromeCls, buttonClass: _btnCls, ...rest } = props;

  return rest;
});

</script>

<style scoped></style>
