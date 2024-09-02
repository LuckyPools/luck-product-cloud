<template>
  <router-link to="/" class="w-full flex-center nowrap-hidden">
    <SystemLogo class="text-32px text-primary"/>
    <h2 v-show="showTitle" class="pl-8px text-16px text-primary font-bold transition duration-300 ease-in-out">
      {{ $t('system.title') }}
    </h2>
  </router-link>
</template>
<script>
import SystemLogo from "@/components/common/system-logo/index.vue";

export default {
  name: 'GlobalLogo',
  components: {SystemLogo},
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
