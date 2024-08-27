<template>
  <Transition
      :name="transitionName"
      mode="out-in"
      @before-leave="setContentXScrollable(true)"
      @after-leave="resetScroll"
      @after-enter="setContentXScrollable(false)"
  >
    <KeepAlive :include="route.cacheRoutes">
      <!-- todo 页面绑定key -->
      <router-view
          v-if="app.reloadFlag"
          :class="{ 'p-16px': showPadding }"
          class="flex-grow bg-layout transition-300">
      </router-view>
    </KeepAlive>
  </Transition>
</template>
<script>
import {mapGetters} from "vuex";
import {LAYOUT_SCROLL_EL_ID} from "@/layout/admin-layout/share";

export default {
  name: 'GlobalContent',
  props: {
    showPadding: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {}
  },
  computed: {
    ...mapGetters(['theme', 'app', 'tab', 'route']),
    transitionName() {
      return this.theme.page.animate ? this.theme.page.animateMode : ''
    }
  },
  methods: {
    resetScroll() {
      const el = document.querySelector(`#${LAYOUT_SCROLL_EL_ID}`);
      el?.scrollTo({left: 0, top: 0});
    },
    setContentXScrollable(value) {
      this.$store.dispatch('app/setContentXScrollable', value);
    },
    getTabIdByRoute(route) {
      const {path, query = {}, meta} = route;
      let id = path;
      if (meta.multiTab) {
        const queryKeys = Object.keys(query).sort();
        const qs = queryKeys.map(key => `${key}=${query[key]}`).join('&');
        id = `${path}?${qs}`;
      }
      return id;
    }
  }
}
</script>
<style></style>
