<template>
  <div id="app">
      <ConfigProvider>
          <router-view class="bg-layout" />
      </ConfigProvider>
  </div>
</template>

<script>
import { ConfigProvider } from 'ant-design-vue';

export default {
  name: 'App',
  components:{ ConfigProvider }
}
</script>

<style>
</style>
