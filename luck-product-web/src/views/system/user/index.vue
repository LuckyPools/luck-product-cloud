<template>
  <div class="min-h-500px flex-col-stretch gap-16px overflow-hidden lt-sm:overflow-auto">
    <UserSearch v-model:model="searchParams" @reset="resetSearchParams" @search="getDataByPage" />
    <ACard
        :title="$t('page.manage.user.title')"
        :bordered="false"
        :body-style="{ flex: 1, overflow: 'hidden' }"
        class="flex-col-stretch sm:flex-1-hidden card-wrapper"
    >
      <template #extra>
        <TableHeaderOperation
            v-model:columns="columnChecks"
            :disabled-delete="checkedRowKeys.length === 0"
            :loading="loading"
            @add="handleAdd"
            @delete="handleBatchDelete"
            @refresh="getData"
        />
      </template>
      <ATable
          ref="tableWrapperRef"
          :columns="columns"
          :data-source="data"
          size="small"
          :row-selection="rowSelection"
          :scroll="scrollConfig"
          :loading="loading"
          row-key="id"
          :pagination="mobilePagination"
          class="h-full"
      />
    </ACard>
  </div>
</template>

<script>
import { Button, Popconfirm, Tag } from 'ant-design-vue';
import { fetchGetUserList } from '@/api/system/user';
import { useTable, useTableOperate, useTableScroll } from '@/utils/table';
import { enableStatusRecord, userGenderRecord } from '@/constants/business';
import {$t} from "@/locales";
import UserSearch from "@/views/system/user/modules/user-search.vue";
import TableHeaderOperation from "@/views/system/user/modules/table-header-operation.vue";

const { tableWrapperRef, scrollConfig } = useTableScroll();

const {
  columns,
  columnChecks,
  data,
  getData,
  getDataByPage,
  loading,
  mobilePagination,
  searchParams,
  resetSearchParams
} = useTable({
  apiFn: fetchGetUserList,
  apiParams: {
    current: 1,
    size: 10,
    status: undefined,
    userName: undefined,
    userGender: undefined,
    nickName: undefined,
    userPhone: undefined,
    userEmail: undefined
  },
  columns: () => [
    {
      key: 'index',
      title: $t('common.index'),
      dataIndex: 'index',
      align: 'center',
      width: 64
    },
    {
      key: 'userName',
      dataIndex: 'userName',
      title: $t('page.manage.user.userName'),
      align: 'center',
      minWidth: 100
    },
    {
        key: 'id',
        dataIndex: 'id',
        title: $t('page.manage.user.userName'),
        align: 'center',
        minWidth: 100
    },
    {
      key: 'userGender',
      title: $t('page.manage.user.userGender'),
      align: 'center',
      dataIndex: 'userGender',
      width: 100,
      customRender: ({ record }) => {
        if (record.userGender === null) {
          return null;
        }

        const tagMap = {
          1: 'processing',
          2: 'error'
        };

        const label = $t(userGenderRecord[record.userGender]);

        return <Tag color={tagMap[record.userGender]}>{label}</Tag>;
      }
    },
    {
      key: 'nickName',
      dataIndex: 'nickName',
      title: $t('page.manage.user.nickName'),
      align: 'center',
      minWidth: 100
    },
    {
      key: 'userPhone',
      dataIndex: 'userPhone',
      title: $t('page.manage.user.userPhone'),
      align: 'center',
      width: 120
    },
    {
      key: 'userEmail',
      dataIndex: 'userEmail',
      title: $t('page.manage.user.userEmail'),
      align: 'center',
      minWidth: 200
    },
    {
      key: 'status',
      dataIndex: 'status',
      title: $t('page.manage.user.userStatus'),
      align: 'center',
      width: 100,
      customRender: ({ record }) => {
        if (record.status === null) {
          return null;
        }

        const tagMap = {
          1: 'success',
          2: 'warning'
        };

        const label = $t(enableStatusRecord[record.status]);

        return <Tag color={tagMap[record.status]}>{label}</Tag>;
      }
    },
    {
      key: 'operate',
      title: $t('common.operate'),
      align: 'center',
      width: 130,
      customRender: ({ record }) => (
          <div class="flex-center gap-8px">
            <Button type="primary" ghost size="small" onClick={() => edit(record.id)}>
              {$t('common.edit')}
            </Button>
            <Popconfirm title={$t('common.confirmDelete')} onConfirm={() => handleDelete(record.id)}>
              <Button danger size="small">
                {$t('common.delete')}
              </Button>
            </Popconfirm>
          </div>
      )
    }
  ]
});

const {
  drawerVisible,
  operateType,
  editingData,
  handleAdd,
  handleEdit,
  checkedRowKeys,
  rowSelection,
  onBatchDeleted,
  onDeleted
  // closeDrawer
} = useTableOperate(data, getData);

export default {
  name: 'UserTable',
  components: {TableHeaderOperation, UserSearch},
  props: {},
  data() {
    return {
      columns,
      columnChecks,
      data,
      getData,
      getDataByPage,
      loading,
      mobilePagination,
      searchParams,
      resetSearchParams,
      scrollConfig
    }
  },
  computed: {
    rowSelection,
  },
  methods: {
      handleBatchDelete() {
        onBatchDeleted();
      },

      handleDelete(id) {
          console.log(id);
          onDeleted();
      },

      edit(id) {
          handleEdit(id);
      },

      handleAdd(){
        handleAdd();
      }
  }
}

</script>

<style scoped></style>
