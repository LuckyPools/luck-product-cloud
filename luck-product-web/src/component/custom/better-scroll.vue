<template>
  <div ref="bsWrapper" class="h-full text-left">
    <div ref="bsContent" class="inline-block" :class="{ 'h-full': !isScrollY }">
      <slot></slot>
    </div>
  </div>
</template>

<script>
import { useElementSize } from '@vueuse/core';
import BScroll from '@better-scroll/core';
export default {
    name: 'BetterScroll',
    props: {
        options: {
            /**
             * todo 规范选项属性
             */
            type: Object
        }
    },
    data() {
        return {
            wrapWidth: null,
            width: null,
            height: null,
            instance: null
        }
    },
    watch:{
        wrapWidth() {
            this.instance.refresh();
        },
        width() {
            this.instance.refresh();
        },
        height() {
            this.instance.refresh();
        },
    },
    computed: {
        isScrollY(){
            return this.options.scrollY;
        }
    },
    methods: {
        initBetterScroll() {
            if (!this.$refs.bsWrapper) return;
            const { width: wrapWidth } = useElementSize(this.$refs.bsWrapper);
            this.wrapWidth = wrapWidth;
            const { width, height } = useElementSize(this.$refs.bsContent);
            this.width = width;
            this.height = height;
            this.instance = new BScroll(this.$refs.bsWrapper, this.options);
        }
    },
    mounted() {
        this.initBetterScroll();
    }
}
</script>
<style scoped></style>
