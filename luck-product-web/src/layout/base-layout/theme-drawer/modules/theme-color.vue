<template>
  <div>
    <ADivider class="text-base_text">{{ $t('theme.themeColor.title') }}</ADivider>
    <div class="flex-col-stretch gap-12px">
      <ATooltip placement="topLeft">
        <SettingItem key="recommend-color" :label="$t('theme.recommendColor')">
          <ASwitch v-model:checked="theme.recommendColor"/>
        </SettingItem>
        <template #title>
          <p>
            <span class="pr-12px">{{ $t('theme.recommendColorDesc') }}</span>
            <br/>
            <AButton
                type="link"
                href="https://uicolors.app/create"
                target="_blank"
                rel="noopener noreferrer"
                class="text-gray"
            >
              https://uicolors.app/create
            </AButton>
          </p>
        </template>
      </ATooltip>
      <SettingItem v-for="(_, key) in themeColors" :key="key" :label="$t(`theme.themeColor.${key}`)">
        <template v-if="key === 'info'" #suffix>
          <ACheckbox v-model:checked="theme.isInfoFollowPrimary">
            {{ $t('theme.themeColor.followPrimary') }}
          </ACheckbox>
        </template>
        <ColorPicker
            :color="themeColors[key]"
            :disabled="key === 'info' && theme.isInfoFollowPrimary"
            @update:color="handleUpdateColor($event, key)"
        />
      </SettingItem>
    </div>
  </div>
</template>
<script>
import {mapGetters} from "vuex";
import SettingItem from "@/layout/base-layout/theme-drawer/components/setting-item.vue";
import ColorPicker from "@/layout/color-picker/index.vue";

export default {
  name: 'ThemeColor',
  components: {ColorPicker, SettingItem},
  data() {
    return {}
  },
  computed: {
    ...mapGetters(['theme', 'app']),
    ...mapGetters('theme', {
      themeColors: 'themeColors',
    }),
  },
  methods: {
    handleUpdateColor(color, key) {
      this.$store.dispatch('theme/updateThemeColors', {key: key, color: color});
    }
  }
}
</script>
