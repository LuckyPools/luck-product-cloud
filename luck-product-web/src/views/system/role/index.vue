<template>
    <div class="min-h-500px flex-col-stretch gap-16px overflow-hidden lt-sm:overflow-auto">
      <a-button @click="() => setModal1Visible(true)">表单</a-button>
      <AproModal
          ref="modal"
          :afterClose="handleAfterClose"
          :full-height="true"
          :dialog-style="{ top: '20px' }"
      >
        <template #title>
            <h1>here is title</h1>
        </template>
        <div v-for="(item, index) in generateRange(20)" :key="index">
          {{ index }}
        </div>
      </AproModal>
      <RoleSearch v-if="false" :formModel.sync="searchParams" @reset="resetSearchParams" @search="search" />
      <ACard
          :title="$t('page.manage.user.title')"
          :bordered="false"
          :body-style="{ flex: 1, overflow: 'hidden' }"
          class="flex-col-stretch sm:flex-1-hidden card-wrapper"
      >
        <AproTable
          ref="table"
          :api="api"
          :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
          :columns="columns"
          :where="searchParams"
          @change="handleChange"
        >

        </AproTable>
      </ACard>
    </div>
</template>
<script>
import AproTable from "@/components/common/a-pro-table/index.vue";
import AproModal from "@/components/common/a-pro-modal/index.vue";
import RoleSearch from "@/views/system/role/modules/role-search.vue";
import {pageUsers} from "@/api/system/user";

const columns = [
    {
        title: 'Name',
        dataIndex: 'name',
        width: 200
    },
    {
        title: 'Username',
        dataIndex: 'username',
        width: 200
    },
    {
        title: 'id',
        dataIndex: 'id',
        width: 200
    },
    {
        title: 'id2',
        dataIndex: 'id',
        width: 200
    },
    {
        title: 'id3',
        dataIndex: 'id',
    },
    {
        title: 'id4',
        dataIndex: 'id',
    },
    {
        title: 'id5',
        dataIndex: 'id',
    },
    {
        title: 'id6',
        dataIndex: 'id',
    },
    {
        title: 'id7',
        dataIndex: 'id',
    },
    {
        title: 'id8',
        dataIndex: 'id',
    },
    {
        title: 'id9',
        dataIndex: 'id',
    },
    {
        title: 'id10',
        dataIndex: 'id',
    },
    {
        title: 'id11',
        dataIndex: 'id',
    }
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
    components: {AproTable,RoleSearch,AproModal},
    data() {
        return {
            data,
            columns,
            modal1Visible: false,
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
        openModal(){
            this.$message.info("打开表单")
        },
        search(){
          this.$refs.table.reload();
        },
        setModal1Visible(modal1Visible) {
          if(modal1Visible){
            this.$refs.modal.handleOpen();
          }else{
            this.$refs.modal.handleCancel();
          }
        },
        generateRange(n) {
          return Array.from({ length: n }, (_, i) => i);
        },
        handleAfterClose(){
            //this.$message.info("关闭成功")
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
