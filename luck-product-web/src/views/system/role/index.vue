<template>
    <div>
        <div style="margin-bottom: 16px">
            <a-button type="primary" :disabled="!hasSelected" :loading="loading" @click="start">
                Reload
            </a-button>
            <span style="margin-left: 8px">
        <template v-if="hasSelected">
          {{ `Selected ${selectedRowKeys.length} items` }}
        </template>
      </span>
        </div>
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
    </div>
</template>
<script>
import AproTable from "@/components/common/a-pro-table/index.vue";
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
        key: i,
        name: `Edward King ${i}`,
        age: 32,
        address: `London, Park Lane no. ${i}`,
    });
}

export default {
    components: {AproTable},
    data() {
        return {
            data,
            columns,
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
        }
    }
};
</script>
