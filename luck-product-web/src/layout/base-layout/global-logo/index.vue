<template>
  <router-link to="/" class="w-full flex-center nowrap-hidden">
    <!-- todo 图标 -->
    <div class="text-32px text-primary">SystemLogo</div>
    <h2 v-show="showTitle" class="pl-8px text-16px text-primary font-bold transition duration-300 ease-in-out">
      {{ $t('system.title') }}
    </h2>
  </router-link>
</template>
<script>
export default {
  name: 'GlobalLogo',
  props: {
    showTitle: {
      type: Boolean
    }
  },
  data() {
    return {}
  },
  computed: {},
  methods: {}
}
</script>
<style scoped></style>
