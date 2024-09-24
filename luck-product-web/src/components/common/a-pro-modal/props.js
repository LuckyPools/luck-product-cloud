export const modalProps = {
    prefixCls: String,
    /** 对话框是否可见*/
    visible: Boolean,
    /** 确定按钮 loading*/
    confirmLoading: Boolean,
    /** 标题*/
    title: [String,Function],
    /** 是否显示右上角的关闭按钮*/
    closable: Boolean,
    closeIcon: String,
    /** 点击确定回调*/
    // onOk: (e: React.MouseEvent<any>) => void,
    /** 点击模态框右上角叉、取消按钮、Props.maskClosable 值为 true 时的遮罩层或键盘按下 Esc 时的回调*/
    // onCancel: (e: React.MouseEvent<any>) => void,
    afterClose: Function,
    /** 垂直居中 */
    centered: Boolean,
    /** 宽度*/
    width: {
        type: [String,Number],
        default: '520px'
    },
    /** 底部内容*/
    footer: String,
    /** 确认按钮文字*/
    okText: {
        type: String,
        default: '确定'
    },
    /** 确认按钮类型*/
    okType: {
        type: String,
        default: 'primary'
    },
    /** 取消按钮文字*/
    cancelText: String,
    icon: String,
    /** 点击蒙层是否允许关闭*/
    maskClosable: Boolean,
    /** 强制渲染 Modal*/
    forceRender: Boolean,
    okButtonProps: Object,
    cancelButtonProps: Object,
    destroyOnClose: Boolean,
    wrapClassName: String,
    maskTransitionName: String,
    transitionName: String,
    getContainer: Function,
    zIndex: Number,
    bodyStyle: Object,
    maskStyle: Object,
    mask: Boolean,
    keyboard: Boolean,
    wrapProps: Object,
    focusTriggerAfterClose: Boolean,
    dialogStyle: {
        type: Object,
        default: () => ({})
    }
}

export const customProps= {
    /**
     * 使用尾部插槽
     */
    footSlot: {
        type: Boolean,
        default: false
    },
    /**
     * 是否全屏
     */
    fullHeight:{
        type: Boolean,
        default: false
    }
}

export default {
    ...modalProps,
    ...customProps
};
