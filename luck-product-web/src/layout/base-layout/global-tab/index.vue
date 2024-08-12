<template>
  <DarkModeContainer class="size-full flex-y-center px-16px shadow-tab">
    <div ref="bsWrapper" class="h-full flex-1-hidden">
      <BetterScroll
        ref="bsScroll"
        :options="{ scrollX: true, scrollY: false, click: appStore.isMobile }"
        @click="removeFocus"
      >
        <div
          ref="tabRef"
          class="h-full flex pr-18px"
          :class="[themeStore.tab.mode === 'chrome' ? 'items-end' : 'items-center gap-12px']"
        >
          <ContextMenu
            v-for="tab in tabStore.tabs"
            :key="tab.id"
            :tab-id="tab.id"
            :disabled-keys="getContextMenuDisabledKeys(tab.id)"
          >
            <PageTab
              :[TAB_DATA_ID]="tab.id"
              :mode="themeStore.tab.mode"
              :dark-mode="themeStore.darkMode"
              :active="tab.id === tabStore.activeTabId"
              :active-color="themeStore.themeColor"
              :closable="!tabStore.isTabRetain(tab.id)"
              @click="tabStore.switchRouteByTab(tab)"
              @close="handleCloseTab(tab)"
            >
              <template #prefix>
                <SvgIcon
                  :icon="tab.icon"
                  :local-icon="tab.localIcon"
                  class="inline-block align-text-bottom text-16px"
                />
              </template>
              <div class="max-w-240px ellipsis-text">{{ tab.label }}</div>
            </PageTab>
          </ContextMenu>
        </div>
      </BetterScroll>
    </div>
    <ReloadButton :loading="!appStore.reloadFlag" @click="refresh" />
    <FullScreen :full="appStore.fullContent" @click="appStore.toggleFullContent" />
  </DarkModeContainer>
</template>

<script>
import { useElementBounding } from '@vueuse/core';
import {mapGetters} from "vuex";
import DarkModeContainer from "@/component/common/dark-mode-container.vue";
import BetterScroll from "@/component/custom/better-scroll.vue";
import PageTab from "@/layout/page-tab/index.vue";
import FullScreen from "@/component/common/full-screen.vue";
const TAB_DATA_ID = 'data-tab-id';
export default {
    name: 'GlobalTab',
    components: {FullScreen, PageTab, BetterScroll, DarkModeContainer},
    props: {
        showTitle: {
            type: Boolean
        }
    },
    data() {
        return {
            bsWrapperWidth: null,
            bsWrapperLeft: null
        }
    },
    watch:{
        $route(route){
            this.$store.dispatch(
                'tab/addTab',route);
        },
        'tab.activeTabId':{
            handler() {
                this.scrollToActiveTab();
            }
        }
    },
    computed: {
        ...mapGetters(['app','theme','user','route']),
    },
    mounted() {
        this.init();
        const { width: bsWrapperWidth, left: bsWrapperLeft } = useElementBounding(this.$refs.bsWrapper);
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

            if (bsScroll.value?.instance) {
                const { maxScrollX, x: leftX, scrollBy } = bsScroll.value.instance;

                const rightX = maxScrollX - leftX;
                const update = deltaX > 0 ? Math.max(-deltaX, rightX) : Math.min(-deltaX, -leftX);

                scrollBy(update, 0, 300);
            }
        },
        getContextMenuDisabledKeys(tabId: string) {
            const disabledKeys: App.Global.DropdownKey[] = [];

            if (tabStore.isTabRetain(tabId)) {
                const homeDisable: App.Global.DropdownKey[] = ['closeCurrent', 'closeLeft'];
                disabledKeys.push(...homeDisable);
            }

            return disabledKeys;
        },

        handleCloseTab(tab: App.Global.Tab) {
            await tabStore.removeTab(tab.id);
            await routeStore.reCacheRoutesByKey(tab.routeKey);
        },

        refresh() {
            appStore.reloadPage(500);
        },

        removeFocus() {
            (document.activeElement as HTMLElement)?.blur();
        },

        init() {
            this.$store.dispatch(
                'tab/initTabStore',this.$route);
        }
    }
}
</script>

<style scoped></style>
