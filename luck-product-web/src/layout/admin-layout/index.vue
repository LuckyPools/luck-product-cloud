<template>
  <div class="relative h-full" :class="[commonClass]" :style="cssVars">
    <div
      :id="isWrapperScroll ? scrollElId : undefined"
      class="h-full flex flex-col"
      :class="[commonClass, scrollWrapperClass, { 'overflow-y-auto': isWrapperScroll }]"
    >
      <!-- Header -->
      <template v-if="showHeader">
        <header
          v-show="!fullContent"
          class="flex-shrink-0"
          :class="[
            'layout-header',
            commonClass,
            headerClass,
            headerLeftGapClass,
            { 'absolute top-0 left-0 w-full': fixedHeaderAndTab }
          ]"
        >
          <slot name="header"></slot>
        </header>
        <div
          v-show="!fullContent && fixedHeaderAndTab"
          class="flex-shrink-0 overflow-hidden"
          :class="['layout-header-placement']"
        ></div>
      </template>

      <!-- Tab -->
      <template v-if="showTab">
        <div
          class="flex-shrink-0"
          :class="[
            'layout-tab',
            commonClass,
            tabClass,
            { 'top-0!': fullContent || !showHeader },
            leftGapClass,
            { 'absolute left-0 w-full': fixedHeaderAndTab }
          ]"
        >
          <slot name="tab"></slot>
        </div>
        <div
          v-show="fullContent || fixedHeaderAndTab"
          class="flex-shrink-0 overflow-hidden"
          :class="['layout-tab-placement']"
        ></div>
      </template>

      <!-- Sider -->
      <template v-if="showSider">
        <aside
          v-show="!fullContent"
          class="absolute left-0 top-0 h-full"
          :class="[
            commonClass,
            siderClass,
            siderPaddingClass,
            siderCollapse ? 'layout-sider_collapsed' : 'layout-sider'
          ]"
        >
          <slot name="sider"></slot>
        </aside>
      </template>

      <!-- Mobile Sider -->
      <template v-if="showMobileSider">
        <aside
          class="absolute left-0 top-0 h-full w-0 bg-white"
          :class="[
            commonClass,
            mobileSiderClass,
            'layout-mobile-sider',
            siderCollapse ? 'overflow-hidden' : 'layout-sider'
          ]"
        >
          <slot name="sider"></slot>
        </aside>
        <div
          v-show="!siderCollapse"
          class="absolute left-0 top-0 h-full w-full bg-[rgba(0,0,0,0.2)]"
          :class="['layout-mobile-sider-mask']"
          @click="handleClickMask"
        ></div>
      </template>

      <!-- Main Content -->
      <main
        :id="isContentScroll ? scrollElId : undefined"
        class="flex flex-col flex-grow"
        :class="[commonClass, contentClass, leftGapClass, { 'overflow-y-auto': isContentScroll }]"
      >
        <slot></slot>
      </main>

      <!-- Footer -->
      <template v-if="showFooter">
        <footer
          v-show="!fullContent"
          class="flex-shrink-0"
          :class="[
            'layout-footer',
            commonClass,
            footerClass,
            footerLeftGapClass,
            { 'absolute left-0 bottom-0 w-full': fixedFooter }
          ]"
        >
          <slot name="footer"></slot>
        </footer>
        <div
          v-show="!fullContent && fixedFooter"
          class="flex-shrink-0 overflow-hidden"
          :class="['layout-footer-placement']"
        ></div>
      </template>
    </div>
  </div>
</template>

<script>
import { AdminLayoutProps } from './props.js';
import { LAYOUT_MAX_Z_INDEX, LAYOUT_SCROLL_EL_ID, createLayoutCssVars } from './shared';

export default {
    name: 'AdminLayout',
    props: {
        ...AdminLayoutProps,
        mode: {
          type: String,
          default: 'vertical'
        },
        scrollMode: {
            type: String,
            default: 'content'
        },
        scrollElId: {
            type: String,
            default: LAYOUT_SCROLL_EL_ID
        },
        commonClass: {
            type: String,
            default: 'transition-all-300'
        },
        fixedTop: {
            type: Boolean,
            default: true
        },
        maxZIndex: {
            type: Number,
            default: LAYOUT_MAX_Z_INDEX
        },
        headerVisible: {
            type: Boolean,
            default: true
        },
        headerHeight: {
            type: Number,
            default: 56
        },
        tabVisible: {
            type: Boolean,
            default: true
        },
        tabHeight: {
            type: Number,
            default: 48
        },
        siderVisible: {
            type: Boolean,
            default: true
        },
        siderCollapse: {
            type: Boolean,
            default: false
        },
        siderWidth: {
            type: Number,
            default: 220
        },
        siderCollapsedWidth: {
            type: Number,
            default: 64
        },
        footerVisible: {
            type: Boolean,
            default: true
        },
        footerHeight: {
            type: Number,
            default: 48
        },
        rightFooter: {
            type: Boolean,
            default: false
        }
    },
    data() {
        return {
        }
    },
    computed:{
        cssVars(){
            return createLayoutCssVars(this.$props);
        },
        // config visible
        showHeader(){
            return this.$slots.header && this.headerVisible
        },
        showTab(){
            return this.$slots.tab && this.tabVisible
        },
        showSider(){
            return !this.isMobile && this.$slots.sider && this.siderVisible
        },
        showMobileSider(){
            return this.isMobile && this.$slots.sider && this.siderVisible
        },
        showFooter(){
            return this.$slots.footer && this.footerVisible
        },
        // scroll mode
        isWrapperScroll(){
            return this.scrollMode === 'wrapper'
        },
        isContentScroll(){
            return this.scrollMode === 'content'
        },
        // layout direction
        isVertical(){
            return this.mode === 'vertical'
        },
        isHorizontal(){
            return this.mode === 'horizontal'
        },
        fixedHeaderAndTab(){
            return this.fixedTop || (this.isHorizontal && this.isWrapperScroll)
        },
        // css
        leftGapClass(){
            if (!this.fullContent && this.showSider) {
                return this.siderCollapse ? 'left-gap_collapsed' : 'left-gap';
            }
            return '';
        },
        headerLeftGapClass(){
            return this.isVertical ? this.leftGapClass : ''
        },
        footerLeftGapClass(){
            const condition1 = this.isVertical;
            const condition2 = this.isHorizontal && this.isWrapperScroll && !this.fixedFooter;
            const condition3 = this.isHorizontal && this.rightFooter;
            if (condition1 || condition2 || condition3) {
                return this.leftGapClass;
            }
            return '';
        },
        siderPaddingClass() {
            let cls = '';
            if (this.showHeader && !this.headerLeftGapClass) {
                cls += 'sider-padding-top';
            }
            if (this.showFooter && !this.footerLeftGapClass) {
                cls += ` ${'sider-padding-bottom'}`;
            }
            return cls;
        }
    },
    methods: {
        handleClickMask() {
          this.emit('update:siderCollapse', true);
        }
    }
}
</script>

<style scoped>
@import 'module.css';
</style>
