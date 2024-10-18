<template>
  <!-- template -->
  <div class="h-full flex-col-stretch flex-1-hidden">
    <slot></slot>
    <SimpleScrollbar>
      <div
          v-for="menu in user.menus"
          :key="menu.key"
          class="mx-4px mb-6px flex-col-center cursor-pointer rounded-8px bg-transparent px-4px py-8px transition-300 hover:bg-[rgb(0,0,0,0.08)]"
          :class="{
            'text-primary selected-mix-menu': menu.active,
            'text-white:65 hover:text-white': inverted,
            '!text-white !bg-primary': menu.active && inverted}"
          @click="handleClickMixMenu(menu)"
      >
        <SvgIcon :icon="menu.icon" :class="[app.siderCollapse ? 'text-icon-small' : 'text-icon-large']"/>
        <p
            class="w-full ellipsis-text text-center text-12px transition-height-300"
            :class="[app.siderCollapse ? 'h-0 pt-0' : 'h-20px pt-4px']"
        >
          {{ menu.label }}
        </p>
      </div>
    </SimpleScrollbar>
    <MenuToggler
        arrow-icon
        :collapsed="app.siderCollapse"
        :class="{ 'text-white:88 !hover:text-white': inverted }"
        @click="handleToggle"
    />
  </div>
</template>
<script>
import {mapGetters} from "vuex";
import MenuToggler from "@/components/custom/menu-toggler/index.vue";
import SimpleScrollbar from "@/layouts/modules/page-tab/simple-scrollbar/index.vue";
import {transformColorWithOpacity} from "@/layouts/modules/page-tab/share";
import SvgIcon from "@/components/custom/svg-icon/index.vue";

export default {
  name: 'FirstLevelMenu',
  components: {SvgIcon, SimpleScrollbar, MenuToggler},
  props: {
    inverted: {
      type: Boolean
    },
    activeMenuKey: {
      type: String
    }
  },
  data() {
    return {}
  },
  computed: {
    ...mapGetters(['app', 'theme', 'user', 'route']),

    selectedBgColor() {
      const {darkMode, themeColor} = this.theme;
      const light = transformColorWithOpacity(themeColor, 0.1, '#ffffff');
      const dark = transformColorWithOpacity(themeColor, 0.3, '#000000');
      return darkMode ? dark : light;
    }
  },
  methods: {

    handleClickMixMenu(menu) {
      this.$emit('select', menu);
    },

    handleToggle() {
      this.$store.dispatch('app/toggleSiderCollapse');
    },
  }
}

</script>
<style scoped>
.selected-mix-menu {
  background-color: v-bind(selectedBgColor);
}
</style>
