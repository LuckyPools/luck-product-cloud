<template>
  <DarkModeContainer class="size-full flex-y-center px-16px shadow-tab">
    <div ref="bsWrapper" class="h-full flex-1-hidden">
      <BetterScroll
          ref="bsScroll"
          :options="{ scrollX: true, scrollY: false, click: app.isMobile }"
          @click="removeFocus"
      >
        <div
            ref="tabRef"
            class="h-full flex pr-18px"
            :class="[theme.tab.mode === 'chrome' ? 'items-end' : 'items-center gap-12px']"
        >
          <ContextMenu
              v-for="item in tabs"
              :key="item.id"
              :tab-id="item.id"
              :disabled-keys="getContextMenuDisabledKeys(item.id)"
          >
            <PageTab
                :[TAB_DATA_ID]="item.id"
                :mode="theme.tab.mode"
                :dark-mode="darkMode"
                :active="item.id === tab.activeTabId"
                :active-color="theme.themeColor"
                :closable="!isTabRetain(item.id)"
                @click.native="switchRouteByTab(item)"
                @close="handleCloseTab(item)"
            >
              <template #prefix>
                <SvgIcon
                    :local-icon="item.localIcon"
                    :icon="item.icon"
                    class="inline-block align-text-bottom text-16px"
                />
              </template>
              <div class="max-w-240px ellipsis-text">{{ item.label }}</div>
            </PageTab>
          </ContextMenu>
        </div>
      </BetterScroll>
    </div>
    <ReloadButton :loading="!app.reloadFlag" @click="refresh"/>
    <FullScreen :full="app.fullContent" @click.native="toggleFullContent"/>
  </DarkModeContainer>
</template>

<script>
import {useElementBounding} from '@vueuse/core';
import {mapGetters} from "vuex";
import DarkModeContainer from "@/components/common/dark-mode-container/index.vue";
import BetterScroll from "@/components/custom/better-scroll/index.vue";
import PageTab from "@/layouts/modules/page-tab/index.vue";
import FullScreen from "@/components/common/full-screen/index.vue";
import ContextMenu from "@/layouts/modules/global-tab/context-menu.vue";
import SvgIcon from "@/components/custom/svg-icon/index.vue";
import ReloadButton from "@/components/common/reload-button/index.vue";
import {getFixedTabIds} from "@/store/modules/tab/utils";

const TAB_DATA_ID = 'data-tab-id';
export default {
  name: 'GlobalTab',
  components: {ReloadButton, SvgIcon, ContextMenu, FullScreen, PageTab, BetterScroll, DarkModeContainer},
  data() {
    return {
      bsWrapperWidth: null,
      bsWrapperLeft: null,
      TAB_DATA_ID: TAB_DATA_ID
    }
  },
  watch: {
    $route(route) {
      this.$store.dispatch(
          'tab/addTab', route);
    },
    'tab.activeTabId': {
      handler() {
        this.scrollToActiveTab();
      }
    }
  },
  computed: {
    ...mapGetters(['app', 'theme', 'tab', 'route']),
    ...mapGetters('tab', {
      tabs: 'allTabs',
    }),
    ...mapGetters('theme', {
      darkMode: 'darkMode',
    })
  },
  mounted() {
    this.init();
    const {width: bsWrapperWidth, left: bsWrapperLeft} = useElementBounding(this.$refs.bsWrapper);
    this.bsWrapperWidth = bsWrapperWidth;
    this.bsWrapperLeft = bsWrapperLeft;
  },
  methods: {
    async scrollToActiveTab() {
      await this.$nextTick();
      if (!this.$refs.tabRef) return;

      const {children} = this.$refs.tabRef;

      for (let i = 0; i < children.length; i += 1) {
        const child = children[i];

        const {value: tabId} = (child.attributes)[TAB_DATA_ID];

        if (tabId === this.tab.activeTabId) {
          const {left, width} = child.getBoundingClientRect();
          const clientX = left + width / 2;

          setTimeout(() => {
            this.scrollByClientX(clientX);
          }, 50);

          break;
        }
      }
    },

    scrollByClientX(clientX) {
      const currentX = clientX - this.bsWrapperLeft;
      const deltaX = currentX - this.bsWrapperWidth / 2;

      if (this.$refs.bsScroll?.instance) {
        const {maxScrollX, x: leftX, scrollBy} = this.$refs.bsScroll.instance;

        const rightX = maxScrollX - leftX;
        const update = deltaX > 0 ? Math.max(-deltaX, rightX) : Math.min(-deltaX, -leftX);

        scrollBy(update, 0, 300);
      }
    },

    getContextMenuDisabledKeys(tabId) {
      const disabledKeys = [];
      if (this.isTabRetain(tabId)) {
        const homeDisable = ['closeCurrent', 'closeLeft'];
        disabledKeys.push(...homeDisable);
      }
      return disabledKeys;
    },

    isTabRetain(tabId) {
      if (tabId === this.tab.homeTab?.id) return true;
      const fixedTabIds = getFixedTabIds(this.tab.tabs);
      return fixedTabIds.includes(tabId);
    },

    async handleCloseTab(tab) {
      await this.$store.dispatch('tab/removeTab', (tab.id));
      await this.$store.dispatch('route/reCacheRoutesByKey', (tab.routeKey));
    },

    refresh() {
      this.$store.dispatch('app/reloadPage', 500);
    },

    removeFocus() {
      (document.activeElement)?.blur();
    },

    init() {
      const {width, left} = useElementBounding(this.$refs.bsWrapper);
      this.bsWrapperWidth = width;
      this.bsWrapperLeft = left;
      this.$store.dispatch('tab/initHomeTab');
      this.$store.dispatch('tab/initTabStore', this.$route);
    },

    toggleFullContent() {
      this.$store.dispatch('app/toggleFullContent');
    },

    switchRouteByTab(tab) {
      this.$store.dispatch('tab/switchRouteByTab', tab);
    }
  }
}
</script>

<style scoped></style>
