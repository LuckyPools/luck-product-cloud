<template>
    <ConfigProvider>
        <router-view class="bg-layout" />
    </ConfigProvider>
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
