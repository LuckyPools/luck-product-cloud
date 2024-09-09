<template>
  <APopover placement="bottomRight" trigger="click">
    <AButton size="small">
      <template #icon>
        <SvgIcon icon="icon-ant-design-setting-outlined" class="align-sub text-icon"></SvgIcon>
      </template>
      <span class="ml-8px">{{ $t('common.columnSetting') }}</span>
    </AButton>
    <template #content>
      <VueDraggable v-model="columns" :animation="150" filter=".none_draggable">
        <div
          v-for="item in columns"
          :key="item.key"
          class="h-36px flex-y-center rd-4px hover:(bg-primary bg-opacity-20)"
        >
          <SvgIcon icon="icon-mdi-drag" class="mr-8px h-full cursor-move text-icon"></SvgIcon>
          <ACheckbox v-model:checked="item.checked" class="none_draggable flex-1">
            {{ item.title }}
          </ACheckbox>
        </div>
      </VueDraggable>
    </template>
  </APopover>
</template>
<script>

import {VueDraggable} from "vue-draggable-plus";
import SvgIcon from "@/components/custom/svg-icon/index.vue";

export default {
  name: 'TableColumnSetting',
  components: {SvgIcon, VueDraggable},
  props: {
  },
  data() {
    return {
      columns: []
    }
  },
  computed: {
  },
  methods: {}
}
</script>
<style scoped></style>
