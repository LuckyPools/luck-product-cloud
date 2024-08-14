<template>
  <div class="flex-center flex-wrap gap-x-32px gap-y-16px">
    <div
      v-for="(item, key) in layoutConfig"
      :key="key"
      class="flex cursor-pointer border-2px rounded-6px hover:border-primary"
      :class="[mode === key ? 'border-primary' : 'border-transparent']"
      @click="handleChangeMode(key)"
    >
      <ATooltip :placement="item.placement" :title="$t(themeLayoutModeRecord[key])">
        <div
          class="h-64px w-96px gap-6px rd-4px p-6px shadow dark:shadow-coolGray-5"
          :class="[key.includes('vertical') ? 'flex' : 'flex-col']"
        >
          <slot :name="key"></slot>
        </div>
      </ATooltip>
    </div>
  </div>
</template>
<script>
import {themeLayoutModeRecord} from "@/config/app";
export default {
    name: 'LayoutModeCard',
    props: {
        mode: {
            type: String
        },
        disabled: {
            type: Boolean
        }
    },
    data() {
        return {
            LayoutConfig: {
                vertical: {
                    placement: 'bottom',
                    headerClass: '',
                    menuClass: 'w-1/3 h-full',
                    mainClass: 'w-2/3 h-3/4'
                },
                'vertical-mix': {
                    placement: 'bottom',
                    headerClass: '',
                    menuClass: 'w-1/4 h-full',
                    mainClass: 'w-2/3 h-3/4'
                },
                horizontal: {
                    placement: 'bottom',
                    headerClass: '',
                    menuClass: 'w-full h-1/4',
                    mainClass: 'w-full h-3/4'
                },
                'horizontal-mix': {
                    placement: 'bottom',
                    headerClass: '',
                    menuClass: 'w-full h-1/4',
                    mainClass: 'w-2/3 h-3/4'
                }
            }
        }
    },
    computed: {
        themeLayoutModeRecord() {
            return themeLayoutModeRecord
        }
    },
    methods: {
        handleChangeMode(mode) {
            if (this.disabled) return;
            this.$emit('update:mode', mode);
        }
    }
}
</script>
<style scoped></style>
