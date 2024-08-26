<template>
  <ADropdown :trigger="trigger" placement="bottomCenter" destroy-popup-on-hide>
    <slot></slot>
    <template #overlay>
      <AMenu v-on:click="dropdownAction">
        <AMenuItem
            v-for="option in options"
            :key="option.key"
            :disabled="option.disabled"
        >
          <div class="flex-y-center gap-12px">
            <SvgIcon :icon="option.icon" class="text-icon"/>
            <span>{{ option.label }}</span>
          </div>
        </AMenuItem>
      </AMenu>
    </template>
  </ADropdown>
</template>
<script>

import SvgIcon from "@/component/custom/svg-icon.vue";

export default {
  name: 'ContextMenu',
  components: {SvgIcon},
  props: {
    tabId: {
      type: String
    },
    trigger: {
      /** 'click' | 'hover' | 'contextmenu' **/
      type: Array,
      default: () => {
        return ['contextmenu']
      }
    },
    excludeKeys: {
      /** 'closeCurrent' | 'closeOther' | 'closeLeft' | 'closeRight' | 'closeAll' **/
      type: Array,
      default: () => {
        return []
      }
    },
    disabledKeys: {
      /** 'closeCurrent' | 'closeOther' | 'closeLeft' | 'closeRight' | 'closeAll' **/
      type: Array,
      default: () => {
        return []
      }
    },
  },
  data() {
    return {}
  },
  computed: {
    options() {
      const opts = [
        {
          key: 'closeCurrent',
          label: this.$t('dropdown.closeCurrent'),
          icon: 'ant-design:close-outlined'
        },
        {
          key: 'closeOther',
          label: this.$t('dropdown.closeOther'),
          icon: 'ant-design:column-width-outlined'
        },
        {
          key: 'closeLeft',
          label: this.$t('dropdown.closeLeft'),
          icon: 'mdi:format-horizontal-align-left'
        },
        {
          key: 'closeRight',
          label: this.$t('dropdown.closeRight'),
          icon: 'mdi:format-horizontal-align-right'
        },
        {
          key: 'closeAll',
          label: this.$t('dropdown.closeAll'),
          icon: 'ant-design:line-outlined'
        }
      ];
      const excludeKeys = this.excludeKeys;
      const disabledKeys = this.disabledKeys;
      const result = opts.filter(opt => !excludeKeys.includes(opt.key));
      disabledKeys.forEach(key => {
        const opt = result.find(item => item.key === key);
        if (opt) {
          opt['disabled'] = true;
        }
      });
      return result;
    }
  },
  methods: {
    dropdownAction({key}) {
      if (key === 'closeCurrent') {
        this.$store.dispatch('tab/removeTab', this.tabId);
      } else if (key === 'closeOther') {
        this.$store.dispatch('tab/clearTabs', [this.tabId]);
      } else if (key === 'closeLeft') {
        this.$store.dispatch('tab/clearLeftTabs', this.tabId);
      } else if (key === 'closeRight') {
        this.$store.dispatch('tab/clearRightTabs', this.tabId);
      } else if (key === 'closeAll') {
        this.$store.dispatch('tab/clearTabs');
      }
    }
  }
}


</script>
<style scoped></style>
