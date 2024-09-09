<template>
  <div>
    <ADivider class="text-base_text!">{{ $t('theme.themeSchema.title') }}</ADivider>
    <div class="flex-col-stretch gap-16px">
      <div class="i-flex-center">
        <SegmentedSwitch v-model="theme.themeScheme" :options="options" class="bg-layout!" @change="handleSegmentChange">
          <template #label="{ payload }">
            <div class="w-[70px] flex justify-center">
              <SvgIcon :icon="payload.icon" class="h-28px text-icon-small flex! items-center"/>
            </div>
          </template>
        </SegmentedSwitch>
      </div>
      <Transition name="sider-inverted">
        <SettingItem v-if="showSiderInverted" :label="$t('theme.sider.inverted')">
          <ASwitch v-model:checked="theme.sider.inverted"/>
        </SettingItem>
      </Transition>
      <SettingItem :label="$t('theme.grayscale')">
        <ASwitch v-model:checked="theme.grayscale" @change="handleGrayscaleChange"/>
      </SettingItem>
    </div>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import {themeSchemaRecord} from "@/config/app";
import SettingItem from "@/layouts/modules/theme-drawer/components/setting-item.vue";
import SvgIcon from "@/components/custom/svg-icon/index.vue";
import SegmentedSwitch from "@/components/common/segmented-switch/index.vue";

export default {
  name: 'DarkMode',
  // eslint-disable-next-line vue/no-unused-components
  components: {SegmentedSwitch, SvgIcon, SettingItem},
  data() {
    return {
      icons: {
        light: 'material-symbols:sunny',
        dark: 'material-symbols:nightlight-rounded',
        auto: 'material-symbols:hdr-auto'
      }
    }
  },
  computed: {
    ...mapGetters(['theme']),
    ...mapGetters('theme', {
      darkMode: 'darkMode',
    }),
    options() {
      return this.getSegmentOptions();
    },
    showSiderInverted() {
      return !this.darkMode && this.theme.layout.mode === 'vertical'
    },
  },
  methods: {
    getSegmentOptions() {
      const opts = Object.keys(themeSchemaRecord).map(item => {
        const key = item;
        return {
          value: item,
          payload: {
            icon: this.icons[key]
          }
        };
      });
      return opts;
    },

    handleSegmentChange(value) {
      this.$store.dispatch('theme/setThemeScheme', value);
    },

    handleGrayscaleChange(checked) {
      this.$store.dispatch('theme/setGrayscale', checked);
    }
  }
}

</script>

<style scoped>
.sider-inverted-enter-active,
.sider-inverted-leave-active {
  --uno: h-22px transition-all-300;
}

.sider-inverted-enter-from,
.sider-inverted-leave-to {
  --uno: translate-x-20px opacity-0 h-0;
}
</style>
