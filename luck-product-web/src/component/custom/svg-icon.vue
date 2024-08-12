<template>
    <div>
        <template v-if="renderLocalIcon">
            <svg aria-hidden="true" width="1em" height="1em" v-bind="bindAttrs">
                <use :xlink:href="symbolId" fill="currentColor"/>
            </svg>
        </template>
        <template v-else>
            <Icon v-if="icon" :icon="icon" v-bind="bindAttrs"/>
        </template>
    </div>
</template>
<script>
import {Icon} from '@iconify/vue2';

export default {
    name: 'SvgIcon',
    inheritAttrs: false,
    components: {Icon},
    props: {
        icon: {
            type: String
        },
        localIcon: {
            type: String
        }
    },
    data() {
        return {}
    },
    computed: {
        bindAttrs() {
            return {
                class: this.class || '',
                style: this.style || ''
            }
        },

        symbolId() {
            // todo 图标前缀
            // const {VITE_ICON_LOCAL_PREFIX: prefix} = import.meta.env;
            const prefix = '';
            const defaultLocalIcon = 'no-icon';
            const icon = this.localIcon || defaultLocalIcon;
            return `#${prefix}-${icon}`;
        },

        /** If localIcon is passed, render localIcon first */
        renderLocalIcon() {
            return this.localIcon || !this.icon
        }
    },
    methods: {}
}
</script>
<style scoped></style>
