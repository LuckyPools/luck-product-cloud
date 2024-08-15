<template>
  <AdminLayout
    v-model:sider-collapse="app.siderCollapse"
    :mode="layoutMode"
    :scroll-el-id="LAYOUT_SCROLL_EL_ID"
    :scroll-mode="theme.layout.scrollMode"
    :is-mobile="app.isMobile"
    :full-content="app.fullContent"
    :fixed-top="theme.fixedHeaderAndTab"
    :header-height="theme.header.height"
    :tab-visible="theme.tab.visible"
    :tab-height="theme.tab.height"
    :content-class="app.contentXScrollable ? 'overflow-x-hidden' : ''"
    :sider-visible="siderVisible"
    :sider-width="siderWidth"
    :sider-collapsed-width="siderCollapsedWidth"
    :footer-visible="theme.footer.visible"
    :footer-height="theme.footer.height"
    :fixed-footer="theme.footer.fixed"
    :right-footer="theme.footer.right"
  >
    <template #header>
      <GlobalHeader v-bind="headerProps" />
    </template>
    <template #tab>
      <GlobalTab />
    </template>
    <template #sider>
      <GlobalSider />
    </template>
    <GlobalContent />
    <ThemeDrawer />
    <template #footer>
      <GlobalFooter />
    </template>
  </AdminLayout>
</template>
<script>
import { LAYOUT_SCROLL_EL_ID } from '../admin-layout/shared.js';
import {mapGetters} from "vuex";
import AdminLayout from "@/layout/admin-layout/index.vue";
import GlobalHeader from "@/layout/base-layout/global-header/index.vue";
import GlobalTab from "@/layout/base-layout/global-tab/index.vue";
import GlobalSider from "@/layout/base-layout/global-sider/index.vue";
import GlobalContent from "@/layout/base-layout/global-content/index.vue";
import ThemeDrawer from "@/layout/base-layout/theme-drawer/index.vue";
import GlobalFooter from "@/layout/base-layout/global-footer/index.vue";

export default {
    name: 'BaseLayout',
    components: {GlobalFooter, ThemeDrawer, GlobalContent, GlobalSider, GlobalTab, GlobalHeader, AdminLayout},
    data() {
        return {
            LAYOUT_SCROLL_EL_ID: LAYOUT_SCROLL_EL_ID,
            headerPropsConfig: {
                vertical: {
                    showLogo: false,
                    showMenu: false,
                    showMenuToggler: true
                },
                'vertical-mix': {
                    showLogo: false,
                    showMenu: false,
                    showMenuToggler: false
                },
                horizontal: {
                    showLogo: true,
                    showMenu: true,
                    showMenuToggler: false
                },
                'horizontal-mix': {
                    showLogo: true,
                    showMenu: true,
                    showMenuToggler: false
                }
            }
        }
    },
    computed: {
        ...mapGetters(['theme','user','app']),

        layoutMode () {
            const vertical = 'vertical';
            const horizontal = 'horizontal';
            return this.theme.layout.mode.includes(vertical) ? vertical : horizontal;
        },

        headerProps () {
            return this.headerPropsConfig[this.theme.layout.mode];
        },

        siderVisible () {
            return this.theme.layout.mode !== 'horizontal';
        },

        isVerticalMix () {
            return this.theme.layout.mode === 'vertical-mix';
        },

        isHorizontalMix (){
            return this.theme.layout.mode === 'horizontal-mix';
        },

        siderWidth () {
          return this.getSiderWidth();
        },

        siderCollapsedWidth () {
          return this.getSiderCollapsedWidth();
        }

    },
    methods: {
        getSiderWidth() {
            const {width, mixWidth, mixChildMenuWidth} = this.theme.sider;

            let w = this.isVerticalMix || this.isHorizontalMix ? mixWidth : width;

            if (this.isVerticalMix && this.app.mixSiderFixed && this.user.menus.length) {
                w += mixChildMenuWidth;
            }

            return w;
        },
        getSiderCollapsedWidth() {
            const {collapsedWidth, mixCollapsedWidth, mixChildMenuWidth} = this.theme.sider;

            let w = this.isVerticalMix || this.isHorizontalMix ? mixCollapsedWidth : collapsedWidth;

            if (this.isVerticalMix && this.app.mixSiderFixed && this.user.menus.length) {
                w += mixChildMenuWidth;
            }

            return w;
        }
    }
}

</script>

<style>
#__SCROLL_EL_ID__ {
  @include scrollbar();
}
</style>
