<template>
  <div ref="domRef"></div>
</template>

<script>
import ColorPicker from '@simonwep/pickr';
import '@simonwep/pickr/dist/themes/nano.min.css';

export default {
    name: 'ColorPicker',
    props: {
        color: {
            type: String
        },
        palettes: {
            type: Array,
            default: () => {
                return [
                    '#3b82f6',
                    '#6366f1',
                    '#8b5cf6',
                    '#a855f7',
                    '#0ea5e9',
                    '#06b6d4',
                    '#f43f5e',
                    '#ef4444',
                    '#ec4899',
                    '#d946ef',
                    '#f97316',
                    '#f59e0b',
                    '#eab308',
                    '#84cc16',
                    '#22c55e',
                    '#10b981',
                    '#14b8a6'
                ]
            }
        },
        disabled: {
            type: Boolean
        },
    },
    data() {
        return {
            domRef: null,
            instance: null
        }
    },
    computed: {},
    watch:{
        color(value){
            this.updateColor(value);
        },
        disabled(value){
            this.updateDisabled(value);
        }
    },
    methods: {
        handleColorChange(hsva) {
            const color = hsva.toHEXA().toString();
            this.$emit('update:color', color);
        },

        initColorPicker() {
            if (!this.domRef) return;

            this.instance = ColorPicker.create({
                el: this.domRef,
                theme: 'nano',
                swatches: this.palettes,
                lockOpacity: true,
                default: this.color,
                disabled: this.disabled,
                components: {
                    preview: true,
                    opacity: false,
                    hue: true,
                    interaction: {
                        hex: true,
                        rgba: true,
                        input: true
                    }
                }
            });

            this.instance.on('change', this.handleColorChange);
        },

        updateColor(color) {
            if (!this.instance) return;
            this.instance.setColor(color);
        },

        updateDisabled(disabled) {
            if (!this.instance) return;

            if (disabled) {
                this.instance.disable();
            } else {
                this.instance.enable();
            }
        }
    },

    mounted() {
        this.domRef = this.$refs.domRef;
        this.initColorPicker();
    }
}

</script>

<style scoped></style>
