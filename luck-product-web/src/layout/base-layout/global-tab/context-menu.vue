<template>
  <ADropdown :trigger="trigger" placement="bottomCenter" destroy-popup-on-hide>
    <slot></slot>
    <template #overlay>
      <AMenu>
        <AMenuItem
          v-for="option in options"
          :key="option.key"
          :disabled="option.disabled"
          @click="dropdownAction[option.key]"
        >
          <div class="flex-y-center gap-12px">
            <SvgIcon :icon="option.icon" class="text-icon" />
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
      default: ['contextmenu']
    },
    excludeKeys: {
      /** 'closeCurrent' | 'closeOther' | 'closeLeft' | 'closeRight' | 'closeAll' **/
      type: Array,
      default: []
    },
    disabledKeys: {
      /** 'closeCurrent' | 'closeOther' | 'closeLeft' | 'closeRight' | 'closeAll' **/
      type: Array,
      default: []
    },
  },
  data() {
    return {}
  },
  computed: {
    options(){
      const opts = [
        {
          key: 'closeCurrent',
          label: $t('dropdown.closeCurrent'),
          icon: 'ant-design:close-outlined'
        },
        {
          key: 'closeOther',
          label: $t('dropdown.closeOther'),
          icon: 'ant-design:column-width-outlined'
        },
        {
          key: 'closeLeft',
          label: $t('dropdown.closeLeft'),
          icon: 'mdi:format-horizontal-align-left'
        },
        {
          key: 'closeRight',
          label: $t('dropdown.closeRight'),
          icon: 'mdi:format-horizontal-align-right'
        },
        {
          key: 'closeAll',
          label: $t('dropdown.closeAll'),
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
    dropdownAction(key){
      if(key === 'closeCurrent'){

      }
      closeCurrent() {
        removeTab(props.tabId);
      },
      closeOther() {
        clearTabs([props.tabId]);
      },
      closeLeft() {
        clearLeftTabs(props.tabId);
      },
      closeRight() {
        clearRightTabs(props.tabId);
      },
      closeAll() {
        clearTabs();
      }
    }
  }
}


</script>
<style scoped></style>
