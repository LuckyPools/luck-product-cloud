<template>
  <div
      class="m-segmented"
      :class="{
      'segmented-small': size === 'small',
      'segmented-large': size === 'large',
      'segmented-block': block,
      'bg-layout': darkMode,
      'm-segmented-dark': darkMode
    }"
  >
    <div class="m-segmented-group">
      <div
          class="m-segmented-item"
          :class="{
          'segmented-item-selected': value === getOptionValue(option),
          'segmented-item-disabled': disabled || getOptionDisabled(option),
          'segmented-item-block': block
        }"
          v-for="(option, index) in options"
          :key="index"
          @click="disabled || getOptionDisabled(option) ? () => false : onSelected(getOptionValue(option))"
      >
        <input
            type="radio"
            class="segmented-item-input"
            :checked="value === getOptionValue(option)"
            :disabled="disabled || getOptionDisabled(option)"
        />
        <div
            class="segmented-item-label"
            :title="typeof option === 'object' && option.payload ? undefined : String(getOptionLabel(option))"
        >
          <slot
              name="label"
              :label="getOptionLabel(option)"
              :payload="typeof option === 'object' ? option.payload : {}"
          >{{ getOptionLabel(option) }}
          </slot
          >
        </div>
      </div>
    </div>
  </div>
</template>
<script>

import darkMode from "@/layouts/modules/theme-drawer/modules/dark-mode/index.vue";
import {mapGetters} from "vuex";

export default {
  name: 'SegmentedSwitch',
  props: {
    // 是否将宽度调整为父元素宽度，同时所有选项占据相同的宽度
    block: {
      type: Boolean,
      default: false
    },
    // 是否禁用
    disabled: {
      type: Boolean,
      default: false
    },
    // 选项数据
    options: {
      type: Array,
      default() {
        return []
      }
    },
    // 控件尺寸: 'large' | 'middle' | 'small'
    size: {
      type: String,
      default: 'middle'
    },
    // 当前选中的值：v-model
    value: {
      type: [String, Number],
      default: undefined
    }
  },
  data() {
    return {}
  },
  computed: {
    ...mapGetters('theme', {
      darkMode: 'darkMode',
    }),
  },
  methods: {
    onSelected(value) {
      if (value !== this.value) {
        this.$emit('update:value', value)
        this.$emit('change', value)
      }
    },
    getOptionDisabled(option) {
      if (typeof option == 'object') {
        return option?.disabled || false
      }
      return false
    },
    getOptionValue(option) {
      if (typeof option == 'object') {
        return option.value
      }
      return option
    },
    getOptionLabel(option) {
      if (typeof option == 'object') {
        return option.label
      }
      return option
    }
  }
}
</script>

<style lang="scss" scoped>
.m-segmented {
  display: inline-block;
  padding: 2px;
  color: rgba(0, 0, 0, 0.65);
  font-size: 14px;
  line-height: 1.5714285714285714;
  background-color: #f5f5f5;
  border-radius: 6px;
  transition: all 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);

  .m-segmented-group {
    position: relative;
    display: flex;
    align-items: stretch;
    justify-items: flex-start;
    width: 100%;

    .m-segmented-item {
      position: relative;
      text-align: center;
      cursor: pointer;
      transition: color 0.2s cubic-bezier(0.645, 0.045, 0.355, 1),
      background-color 0.2s;
      border-radius: 4px;

      &:hover:not(.segmented-item-selected):not(.segmented-item-disabled) {
        color: rgba(0, 0, 0, 0.88);

        &::after {
          background-color: rgba(0, 0, 0, 0.06);
        }
      }

      &::after {
        position: absolute;
        width: 100%;
        height: 100%;
        top: 0;
        inset-inline-start: 0;
        border-radius: inherit;
        transition: background-color 0.2s;
        pointer-events: none;
        content: '';
      }

      .segmented-item-input {
        position: absolute;
        inset-block-start: 0;
        inset-inline-start: 0;
        width: 0;
        height: 0;
        opacity: 0;
        pointer-events: none;
      }

      .segmented-item-label {
        min-height: 28px;
        line-height: 28px;
        padding: 0 11px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
      }
    }

    .segmented-item-selected {
      background-color: #ffffff;
      box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.03),
      0 1px 6px -1px rgba(0, 0, 0, 0.02),
      0 2px 4px 0 rgba(0, 0, 0, 0.02);
      color: rgba(0, 0, 0, 0.88);
    }

    .segmented-item-disabled {
      color: rgba(0, 0, 0, 0.25);
      cursor: not-allowed;
    }
  }
}

.m-segmented-dark{
  color: rgba(255, 255, 255, 0.65);

  .m-segmented-group {

    .m-segmented-item {

      &:hover:not(.segmented-item-selected):not(.segmented-item-disabled) {
        color: rgba(255, 255, 255, 0.85);
      }
    }

    .segmented-item-selected {
      background-color: #1f1f1f;
      color: rgba(255, 255, 255, 0.85);
    }
  }
}

.segmented-small {
  border-radius: 4px;

  .m-segmented-group .m-segmented-item {
    border-radius: 2px;

    .segmented-item-label {
      min-height: 20px;
      line-height: 20px;
      padding: 0 7px;
    }
  }
}

.segmented-large {
  border-radius: 8px;

  .m-segmented-group .m-segmented-item {
    border-radius: 6px;

    .segmented-item-label {
      min-height: 36px;
      line-height: 36px;
      padding: 0 11px;
      font-size: 16px;
    }
  }
}

.segmented-block {
  display: flex;
  width: 100%;

  .m-segmented-group .m-segmented-item {
    flex: 1;
    min-width: 0;
  }
}
</style>
