<template>
  <div class="flex flex-wrap justify-end gap-x-12px gap-y-8px lt-sm:(w-200px py-12px)">
    <slot name="prefix"></slot>
    <slot name="default">
      <AButton size="small" ghost type="primary" @click="add">
        <template #icon>
          <SvgIcon icon="icon-ic-round-plus" class="align-sub text-icon"></SvgIcon>
        </template>
        <span class="ml-8px">{{ $t('common.add') }}</span>
      </AButton>
      <APopconfirm :title="$t('common.confirmDelete')" :disabled="disabledDelete" @confirm="batchDelete">
        <AButton size="small" danger :disabled="disabledDelete">
          <template #icon>
            <SvgIcon icon="icon-ic-round-delete" class="align-sub text-icon"></SvgIcon>
          </template>

          <span class="ml-8px">{{ $t('common.batchDelete') }}</span>
        </AButton>
      </APopconfirm>
    </slot>
    <AButton size="small" @click="refresh">
      <template #icon>
        <SvgIcon icon="icon-mdi-refresh" class="align-sub text-icon" :class="{ 'animate-spin': loading }"></SvgIcon>
      </template>
      <span class="ml-8px">{{ $t('common.refresh') }}</span>
    </AButton>
    <TableColumnSetting v-model:columns="columns" />
    <slot name="suffix"></slot>
  </div>
</template>
<script>

import SvgIcon from "@/components/custom/svg-icon/index.vue";
import TableColumnSetting from "@/views/system/user/modules/table-column-setting.vue";

export default {
  name: 'TableHeaderOperation',
  components: {TableColumnSetting, SvgIcon},
  props: {
    disabledDelete: {
      type: Boolean
    },
    loading: {
      type: Boolean
    }
  },
  data() {
    return {
      columns: []
    }
  },
  computed: {
  },
  methods: {
    add() {
      this.$emit('add');
    },

    batchDelete() {
      this.$emit('delete');
    },

    refresh() {
      this.$emit('refresh');
    }
  }
}


</script>
<style scoped></style>
