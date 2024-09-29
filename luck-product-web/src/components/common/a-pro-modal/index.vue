<template>
  <div>
    <a-modal
        ref="modal"
        :confirmLoading="confirmLoading"
        :closable="closable"
        :closeIcon="closeIcon"
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
        :visible="formVisible"
        :width="formWidth"
        :afterClose="handleAfterClose"
        @ok="handleOk"
        @cancel="handleCancel"
        :class="[{'full-height': formFullHeight}]"
    >
      <template #title>
          <div class="flex justify-between items-center">
            <slot name="title">
              <span>{{title}}</span>
            </slot>
            <SvgIcon v-if="!formFullHeight" v-on:click.native="toggleFullHeight" icon="mdi:fullscreen" class="mr-10! cursor-pointer"/>
            <SvgIcon v-else v-on:click.native="toggleFullHeight" icon="mdi:fullscreen-exit" class="mr-10! cursor-pointer"/>
          </div>
      </template>
      <template #closeIcon>
        <SvgIcon icon="mdi:close" class="cursor-pointer text-icon-large align-super! text-black"/>
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
      /**
       * 是否可见
       */
      formVisible: false,
      /**
       * 全屏
       */
      formFullHeight: false
    }
  },
  watch: {
    visible(val) {
      if(val){
        this.handleOpen();
      }else{
        this.handleCancel();
      }
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
      if(this.formFullHeight
          && this.width === props.width.default){
        return '780px'
      }
      return this.width
    },
  },
  methods: {
    /**
     * 初始化样式
     */
    initStyle(){
      this.formFullHeight = this.fullHeight
    },

    /**
     * 确认按钮
     * @param e
     */
    handleOk(e) {
      this.$emit('ok', e);
      this.formVisible = false;
    },

    /**
     * 关闭弹窗
     * @param e
     */
    handleCancel(e) {
      this.$emit('cancel', e);
      this.formVisible = false;
    },

    /**
     * 打开弹窗
     */
    handleOpen(){
      this.initStyle();
      this.formVisible = true;
    },

    /**
     * 切换全屏
     */
    toggleFullHeight(){
      this.formFullHeight = !this.formFullHeight;
    },

    /**
     * 关闭弹窗后
     */
    handleAfterClose(){
      this.initStyle();
      if(this.afterClose){
        this.afterClose();
      }
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
