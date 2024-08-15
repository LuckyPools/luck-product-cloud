<template>
  <ADivider>{{ $t('theme.themeColor.title') }}</ADivider>
  <div class="flex-col-stretch gap-12px">
    <ATooltip placement="topLeft">
      <SettingItem key="recommend-color" :label="$t('theme.recommendColor')">
        <ASwitch v-model:checked="theme.recommendColor" />
      </SettingItem>
      <template #title>
        <p>
          <span class="pr-12px">{{ $t('theme.recommendColorDesc') }}</span>
          <br />
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
    <SettingItem v-for="(_, key) in theme.themeColors" :key="key" :label="$t(`theme.themeColor.${key}`)">
      <template v-if="key === 'info'" #suffix>
        <ACheckbox v-model:checked="theme.isInfoFollowPrimary">
          {{ $t('theme.themeColor.followPrimary') }}
        </ACheckbox>
      </template>
      <ColorPicker
        :color="theme.themeColors[key]"
        :disabled="key === 'info' && theme.isInfoFollowPrimary"
        @update:color="handleUpdateColor($event, key)"
      />
    </SettingItem>
  </div>
</template>
<script>
import {mapGetters} from "vuex";

export default {
    name: 'ThemeColor',
    props: {
        showTitle: {
            type: Boolean
        }
    },
    data() {
        return {}
    },
    computed: {
        ...mapGetters(['theme','app']),
    },
    methods: {
        handleUpdateColor(color, key) {
            this.$store.dispatch('theme/updateThemeColors',key,color);
        }
    }
}




function
</script>
<style scoped></style>
