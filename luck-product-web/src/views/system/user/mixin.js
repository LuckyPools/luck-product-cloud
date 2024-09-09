import {cloneDeep} from "lodash-es";
import {computed} from "vue";
import {TableRowSelection} from "ant-design-vue/lib/table/interface";
import {$t} from "@/locales";
import {toValue} from "@vueuse/core/index";

export const mixins = {
    data() {
        return {
            operateType: 'add',
            drawerVisible: false,
            editingData: {},
            data: [],
            checkedRowKeys: {}
        };
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
            this.$message?.success($t('common.deleteSuccess'));
            this.checkedRowKeys = [];
            this.getData();
        },

        getData(){

        },

        onDeleted() {
            this.$message?.success($t('common.deleteSuccess'));
            this.getData();
        }
    },
};
