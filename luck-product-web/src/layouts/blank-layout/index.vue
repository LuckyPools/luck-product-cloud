<template>
  <GlobalContent :show-padding="false" />
</template>
<script>
import GlobalContent from "@/layouts/modules/global-content/index.vue";

export default {
    name: 'BlankLayout',
    components: {GlobalContent}
}
</script>

<style scoped></style>
