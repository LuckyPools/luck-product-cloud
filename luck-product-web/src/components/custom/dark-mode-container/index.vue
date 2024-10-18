<template>
  <div class="bg-container text-base_text transition-300" :class="{ 'bg-inverted text-#1f1f1f': inverted }">
    <slot></slot>
  </div>
</template>
<script>
export default {
  name: 'DarkModeContainer',
  props: {
    inverted: {
      type: Boolean
    }
  },
  data() {
    return {}
  },
  methods: {}
}
</script>
<style scoped></style>
