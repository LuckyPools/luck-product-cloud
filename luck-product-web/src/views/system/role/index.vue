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
                :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
                :columns="columns"
                :data-source="data"
        >
            <template v-slot:action>
                <a href="javascript:;">Delete</a>
            </template>
        </AproTable>
    </div>
</template>
<script>
import AproTable from "@/views/system/user/table.vue";
import {ref} from "vue";

const columns = [
    {
        title: 'Name',
        dataIndex: 'name',
    },
    {
        title: 'Age',
        dataIndex: 'age',
    },
    {
        title: 'Address',
        dataIndex: 'address',
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

function useLoading(initValue = false) {
    const { bool: loading, setTrue: startLoading, setFalse: endLoading } = useBoolean(initValue);

    return {
        loading,
        startLoading,
        endLoading
    };
}

function useBoolean(initValue = false) {
    const bool = ref(initValue);

    function setBool(value) {
        bool.value = value;
    }
    function setTrue() {
        setBool(true);
    }
    function setFalse() {
        setBool(false);
    }
    function toggle() {
        setBool(!bool.value);
    }

    return {
        bool,
        setBool,
        setTrue,
        setFalse,
        toggle
    };
}

export default {
    components: {AproTable},
    data() {
        return {
            data,
            columns,
            selectedRowKeys: [], // Check here to configure the default column
            loading: false,
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
    }
};
</script>
