import {cloneDeep} from "lodash-es";

export const mixins = {
    data() {
        return {
            tableOptions: {

            },
            operateType: 'add',
            drawerVisible: false,
            editingData: {},
            data: [],
            checkedRowKeys: {}
        };
    },
    props: {
        loading: {
            type: Boolean
        },

    },
    computed: {
        rowSelection(){
            return {
                columnWidth: 48,
                type: 'checkbox',
                selectedRowKeys: this.checkedRowKeys,
                onChange: this.onSelectChange
            };
        },

    },
    created() {

    },
    mounted() {

    },
    methods: {
        useTable(config){
            const { apiFn, apiParams, immediate } = config;

        },

        useHookTable(){

        },

        handleAdd() {
            this.operateType = 'add';
            this.openDrawer();
        },

        openDrawer(){
            this.drawerVisible = true;
        },

        closeDrawer(){
            this.drawerVisible = false;
        },

        handleEdit(id) {
            this.operateType = 'edit';
            const findItem = this.data.value.find(item => item.id === id) || null;
            this.editingData = cloneDeep(findItem);
            this.openDrawer();
        },

        onSelectChange(keys) {
            this.checkedRowKeys = keys;
        },

        onBatchDeleted() {
            this.$message?.success(this.$t('common.deleteSuccess'));
            this.checkedRowKeys = [];
            this.getData();
        },

        getData(){

        },

        onDeleted() {
            this.$message?.success(this.$t('common.deleteSuccess'));
            this.getData();
        }
    },
};
