<template>
    <div class="min-h-500px flex-col-stretch gap-16px overflow-hidden lt-sm:overflow-auto">
      <RoleSearch v-model:model="searchParams" @reset="resetSearchParams" @search="getDataByPage" />
      <ACard
          :title="$t('page.manage.user.title')"
          :bordered="false"
          :body-style="{ flex: 1, overflow: 'hidden' }"
          class="flex-col-stretch sm:flex-1-hidden card-wrapper"
      >
        <AproTable
          :api="api"
          :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
          :columns="columns"
          @change="handleChange"
        >
          <template v-slot:action>
            <a href="javascript:;">Delete</a>
          </template>
        </AproTable>
      </ACard>
    </div>
</template>
<script>
import AproTable from "@/components/common/a-pro-table/index.vue";
import RoleSearch from "@/views/system/role/modules/role-search.vue";
import {pageUsers} from "@/api/system/user";

const columns = [
    {
        title: 'Name',
        dataIndex: 'name',
    },
    {
        title: 'Username',
        dataIndex: 'username',
    },
    {
        title: 'id',
        dataIndex: 'id',
    },
    {
        title: 'Action',
        key: 'action',
        scopedSlots: { customRender: 'action' },
    },
];

const data = [];
for (let i = 0; i < 46; i++) {
    data.push({
        id: i,
        username: `Edward King ${i}`,
        age: 32,
        name: `London, Park Lane no. ${i}`,
    });
}

export default {
    components: {AproTable,RoleSearch},
    data() {
        return {
            data,
            columns,
            searchParams: {},
            selectedRowKeys: [], // Check here to configure the default column
            loading: false,
            api: {
              query: pageUsers
            },
        };
    },
    computed: {
        hasSelected() {
            return this.selectedRowKeys.length > 0;
        },
    },
    mounted() {

    },
    methods: {
        start() {
            this.loading = true;
            // ajax request after empty completing
            setTimeout(() => {
                this.loading = false;
                this.selectedRowKeys = [];
            }, 1000);
        },
        onSelectChange(selectedRowKeys) {
            console.log('selectedRowKeys changed: ', selectedRowKeys);
            this.selectedRowKeys = selectedRowKeys;
        },
        handleChange(pagination, filters, sorter, { currentDataSource }){
            console.log('测试分页事件' + pagination)
        },
        resetSearchParams(){

        },
        getDataByPage(){

        }
    }
};
</script>
<style lang="scss">
.ant-advanced-search-form {
  padding: 24px;
  background: #fbfbfb;
  border: 1px solid #d9d9d9;
  border-radius: 6px;
}

.ant-advanced-search-form .ant-form-item {
  display: flex;
}

.ant-advanced-search-form .ant-form-item-control-wrapper {
  flex: 1;
}

#components-form-demo-advanced-search .ant-form {
  max-width: none;
}
#components-form-demo-advanced-search .search-result-list {
  margin-top: 16px;
  border: 1px dashed #e9e9e9;
  border-radius: 6px;
  background-color: #fafafa;
  min-height: 200px;
  text-align: center;
  padding-top: 80px;
}
</style>
