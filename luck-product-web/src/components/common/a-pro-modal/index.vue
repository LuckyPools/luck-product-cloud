<template>
  <div>
    <a-modal
        :visible="visible"
        :confirmLoading="confirmLoading"
        :closable="closable"
        :closeIcon="closeIcon"
        :afterClose="afterClose"
        :centered="centered"
        :footer="footer"
        :okText="okText"
        :okType="okType"
        :cancelText="cancelText"
        :icon="icon"
        :maskClosable="maskClosable"
        :forceRender="forceRender"
        :okButtonProps="okButtonProps"
        :cancelButtonProps="cancelButtonProps"
        :destroyOnClose="destroyOnClose"
        :wrapClassName="wrapClassName"
        :maskTransitionName="maskTransitionName"
        :transitionName="transitionName"
        :getContainer="getContainer"
        :zIndex="zIndex"
        :bodyStyle="bodyStyle"
        :maskStyle="maskStyle"
        :mask="mask"
        :keyboard="keyboard"
        :wrapProps="wrapProps"
        :focusTriggerAfterClose="focusTriggerAfterClose"
        :dialogStyle="formDialogStyle"
        :width="formWidth"
        @ok="handleOk"
        @cancel="handleCancel"
        :class="[{'full-height': formFullHeight}]"
    >
      <template #title>
        <slot name="title">
          <div class="flex justify-between items-center">
            <span>{{title}}</span>
            <SvgIcon v-if="!formFullHeight" v-on:click.native="toggleFullHeight" icon="bi:arrows-angle-expand" class="text-icon-large cursor-pointer"/>
            <SvgIcon v-else v-on:click.native="toggleFullHeight" icon="bi:arrows-angle-contract" class="text-icon-large cursor-pointer"/>
          </div>
        </slot>
      </template>
      <template v-if="footSlot" #footer>
        <slot name="footer"></slot>
      </template>
      <slot></slot>
    </a-modal>
  </div>
</template>

<script>
import props from "@/components/common/a-pro-modal/props";
import SvgIcon from "@/components/custom/svg-icon/index.vue";
export default {
  name: 'AproModal',
  components: {SvgIcon},
  props: {
    ...props
  },
  data() {
    return {
      formFullHeight: false
    }
  },
  computed: {
    formDialogStyle(){
      if(this.formFullHeight){
        return {
          top: '0',
          bottom: '0'
        }
      }else{
        return this.dialogStyle;
      }
    },
    formWidth(){
      console.log(props.width.default)
      if(this.formFullHeight && this.width === props.width.default){
        return '780px'
      }
      return this.width
    },
  },
  created() {
    this.initStyle()
  },
  methods: {
    initStyle(){
      this.formFullHeight = this.fullHeight
    },

    handleOk(e) {
      this.$emit('ok', e);
    },

    handleCancel(e) {
      this.$emit('cancel', e);
    },

    toggleFullHeight(){
      this.formFullHeight = !this.formFullHeight;
    }
  },
};
</script>
<style lang="scss" scoped>

.full-height{

  :deep(.ant-modal-wrap){
    overflow-y: hidden;

    .ant-modal-content{
      top: 0;
      bottom: 0;

      .ant-modal-body{
        height: calc(100vh - 108px);
        overflow-y: auto;
      }
    }
  }
}

</style>
