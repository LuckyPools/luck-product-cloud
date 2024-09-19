<template>
  <a-modal
      :title="title"
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
      :dialogStyle="dialogStyle"
      @ok="handleOk"
      @cancel="handleCancel"
  >
    <template v-if="footSlot" v-slot:footer>
      <slot name="footer"></slot>
    </template>
    <slot></slot>
  </a-modal>
</template>

<script>
import props from "@/components/common/a-pro-modal/props";
export default {
  name: 'AproModal',
  props: {
    ...props
  },
  data() {
    return {
    };
  },
  methods: {
    handleOk(e) {
      this.$emit('ok', e);
    },

    handleCancel(e) {
      this.$emit('cancel', e);
    },
  },
};
</script>
<style scoped></style>
