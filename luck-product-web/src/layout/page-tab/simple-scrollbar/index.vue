<template>
  <div class="h-full flex-1-hidden">
    <Simplebar class="h-full">
      <slot />
    </Simplebar>
  </div>
</template>
<script>
import Simplebar from 'simplebar-vue';
import 'simplebar-vue/dist/simplebar.min.css';
export default {
  name: 'SimpleScrollbar',
  components: {Simplebar},
  props: {
  },
  data() {
    return {}
  },
  computed: {},
  methods: {}
}

</script>
<style scoped></style>
