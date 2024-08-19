<template>
  <div id="app">
      <ConfigProvider>
          <BaseLayout class="bg-layout" ></BaseLayout>
      </ConfigProvider>
  </div>
</template>

<script>
import { ConfigProvider } from 'ant-design-vue';
import BaseLayout from "@/layout/base-layout/index.vue";

export default {
  name: 'App',
  components:{BaseLayout, ConfigProvider }
}
</script>

<style>
</style>
