<template>
  <a-modal
      :visible="visible"
      :confirmLoading="confirmLoading"
      :closable="closable"
      :closeIcon="closeIcon"
      :afterClose="afterClose"
      :centered="centered"
      :width="width"
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
      :dialogStyle="{ top: '0',bottom: '0' }"
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
  methods: {
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
<style scoped>
:deep(.full-height){
  .ant-modal-content{
    position: fixed;
    top: 0;
    bottom: 0;
    width: 400px;

    .ant-modal-body{
      height: calc(100vh - 108px);
    }
  }
}

</style>
