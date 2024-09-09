<template>
  <div>
    <ADivider class="text-base_text!">{{ $t('theme.pageFunTitle') }}</ADivider>
    <TransitionGroup tag="div" name="setting-list" class="flex-col-stretch gap-12px">
      <SettingItem key="1" :label="$t('theme.scrollMode.title')">
        <ASelect v-model:value="theme.layout.scrollMode" class="w-120px">
          <ASelectOption v-for="option in themeScrollModeOptions" :key="option.value" :value="option.value">
            {{ $t(option.label) }}
          </ASelectOption>
        </ASelect>
      </SettingItem>
      <SettingItem key="1-1" :label="$t('theme.page.animate')">
        <ASwitch v-model:checked="theme.page.animate"/>
      </SettingItem>
      <SettingItem v-if="theme.page.animate" key="1-2" :label="$t('theme.page.mode.title')">
        <ASelect v-model:value="theme.page.animateMode" class="w-120px">
          <ASelectOption v-for="option in themePageAnimationModeOptions" :key="option.value" :value="option.value">
            {{ $t(option.label) }}
          </ASelectOption>
        </ASelect>
      </SettingItem>
      <SettingItem v-if="isWrapperScrollMode" key="2" :label="$t('theme.fixedHeaderAndTab')">
        <ASwitch v-model:checked="theme.fixedHeaderAndTab"/>
      </SettingItem>
      <SettingItem key="3" :label="$t('theme.header.height')">
        <AInputNumber v-model:value="theme.header.height" class="w-120px"/>
      </SettingItem>
      <SettingItem key="4" :label="$t('theme.header.breadcrumb.visible')">
        <ASwitch v-model:checked="theme.header.breadcrumb.visible"/>
      </SettingItem>
      <SettingItem v-if="theme.header.breadcrumb.visible" key="4-1" :label="$t('theme.header.breadcrumb.showIcon')">
        <ASwitch v-model:checked="theme.header.breadcrumb.showIcon"/>
      </SettingItem>
      <SettingItem key="5" :label="$t('theme.tab.visible')">
        <ASwitch v-model:checked="theme.tab.visible"/>
      </SettingItem>
      <SettingItem v-if="theme.tab.visible" key="5-1" :label="$t('theme.tab.cache')">
        <ASwitch v-model:checked="theme.tab.cache"/>
      </SettingItem>
      <SettingItem v-if="theme.tab.visible" key="5-2" :label="$t('theme.tab.height')">
        <AInputNumber v-model:value="theme.tab.height" class="w-120px"/>
      </SettingItem>
      <SettingItem v-if="theme.tab.visible" key="5-3" :label="$t('theme.tab.mode.title')">
        <ASelect v-model:value="theme.tab.mode" class="w-120px">
          <ASelectOption v-for="option in themeTabModeOptions" :key="option.value" :value="option.value">
            {{ $t(option.label) }}
          </ASelectOption>
        </ASelect>
      </SettingItem>
      <SettingItem v-if="layoutMode === 'vertical'" key="6-1" :label="$t('theme.sider.width')">
        <AInputNumber v-model:value="theme.sider.width" class="w-120px"/>
      </SettingItem>
      <SettingItem v-if="layoutMode === 'vertical'" key="6-2" :label="$t('theme.sider.collapsedWidth')">
        <AInputNumber v-model:value="theme.sider.collapsedWidth" class="w-120px"/>
      </SettingItem>
      <SettingItem v-if="isMixLayoutMode" key="6-3" :label="$t('theme.sider.mixWidth')">
        <AInputNumber v-model:value="theme.sider.mixWidth" class="w-120px"/>
      </SettingItem>
      <SettingItem v-if="isMixLayoutMode" key="6-4" :label="$t('theme.sider.mixCollapsedWidth')">
        <AInputNumber v-model:value="theme.sider.mixCollapsedWidth" class="w-120px"/>
      </SettingItem>
      <SettingItem v-if="layoutMode === 'vertical-mix'" key="6-5" :label="$t('theme.sider.mixChildMenuWidth')">
        <AInputNumber v-model:value="theme.sider.mixChildMenuWidth" class="w-120px"/>
      </SettingItem>
      <SettingItem key="7" :label="$t('theme.footer.visible')">
        <ASwitch v-model:checked="theme.footer.visible"/>
      </SettingItem>
      <SettingItem v-if="theme.footer.visible && isWrapperScrollMode" key="7-1" :label="$t('theme.footer.fixed')">
        <ASwitch v-model:checked="theme.footer.fixed"/>
      </SettingItem>
      <SettingItem v-if="theme.footer.visible" key="7-2" :label="$t('theme.footer.height')">
        <AInputNumber v-model:value="theme.footer.height" class="w-120px"/>
      </SettingItem>
      <SettingItem
          v-if="theme.footer.visible && layoutMode === 'horizontal-mix'"
          key="7-3"
          :label="$t('theme.footer.right')"
      >
        <ASwitch v-model:checked="theme.footer.right"/>
      </SettingItem>
    </TransitionGroup>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import {themePageAnimationModeOptions, themeScrollModeOptions, themeTabModeOptions} from '@/config/app';
import SettingItem from "@/layouts/modules/theme-drawer/components/setting-item/index.vue";

export default {
  name: 'PageFun',
  components: {SettingItem},
  props: {},
  data() {
    return {
      themePageAnimationModeOptions: themePageAnimationModeOptions,
      themeScrollModeOptions: themeScrollModeOptions,
      themeTabModeOptions: themeTabModeOptions
    }
  },
  computed: {
    ...mapGetters(['theme']),
    layoutMode() {
      return this.theme.layout.mode;
    },
    isMixLayoutMode() {
      return this.layoutMode.indexOf('mix') > -1
    },
    isWrapperScrollMode() {
      return this.theme.layout.scrollMode === 'wrapper'
    }
  },
  methods: {}
}

</script>


<style scoped>
.setting-list-move,
.setting-list-enter-active,
.setting-list-leave-active {
  --uno: transition-all-300;
}

.setting-list-enter-from,
.setting-list-leave-to {
  --uno: opacity-0 -translate-x-30px;
}

.setting-list-leave-active {
  --uno: absolute;
}
</style>
