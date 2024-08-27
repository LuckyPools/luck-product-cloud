<template>
  <ABreadcrumb v-if="theme.header.breadcrumb.visible" v-bind="$props" v-on="$listeners">
    <ABreadcrumbItem v-for="item in breadcrumbs" :key="item.key" >
      <div class="i-flex-y-center align-middle text-base_text">
        <SvgIcon
            :local-icon="item.localIcon"
            :icon="item.icon"
            v-if="theme.header.breadcrumb.showIcon"
            class="mr-4px text-icon"
        />
        {{ item.label }}
      </div>
      <template v-if="item.children?.length" #overlay>
        <AMenu @click="handleClickMenu">
          <AMenuItem v-for="option in item.children" :key="option.key">
            <div class="i-flex-y-center align-middle">
              <SvgIcon
                  :local-icon="option.localIcon"
                  :icon="option.icon"
                  v-if="theme.header.breadcrumb.showIcon"
                  class="mr-4px text-icon"
              />
              {{ option.label }}
            </div>
          </AMenuItem>
        </AMenu>
      </template>
    </ABreadcrumbItem>
  </ABreadcrumb>
</template>
<script>
import {mapGetters} from "vuex";
import SvgIcon from "@/component/custom/svg-icon.vue";

export default {
  name: 'GlobalBreadcrumb',
  components: {SvgIcon},
  inheritAttrs: false,
  data() {
    return {}
  },
  computed: {
    ...mapGetters(['route', 'theme']),
    ...mapGetters('route', {
      breadcrumbs: 'breadcrumbs',
    }),
  },
  methods: {
    handleClickMenu(menuInfo) {
      // todo 切换路由 routerPushByKey(key)
      this.$router.push({name: menuInfo.key, query: {}})
    }
  }
}
</script>
<style>
.ant-breadcrumb > span:last-child {
    color: transparent;
}
</style>
