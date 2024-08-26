<template>
  <ButtonIcon :tooltip-content="$t('icon.reload')">
    <!-- todo 图标 -->
    <a-icon type="reload" :class="{ 'animate-spin animate-duration-750': loading }"/>
  </ButtonIcon>
</template>

<script>
import ButtonIcon from "@/component/custom/button-icon.vue";

export default {
  name: 'ReloadButton',
  components: {ButtonIcon},
  props: {
    loading: {
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
