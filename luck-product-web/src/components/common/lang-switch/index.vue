<template>
  <a-dropdown placement="bottomCenter">
    <ButtonIcon :tooltip-content="tooltipContent" tooltip-placement="left">
      <SvgIcon unicon="heroicons:language"/>
    </ButtonIcon>
    <template #overlay>
      <AMenu :selected-keys="[lang]" @click="changeLang">
        <AMenuItem v-for="option in langOptions" :key="option.key">
          {{ option.label }}
        </AMenuItem>
      </AMenu>
    </template>
  </a-dropdown>
</template>
<script>
import ButtonIcon from "@/components/custom/button-icon/index.vue";
import SvgIcon from "@/components/custom/svg-icon/index.vue";

export default {
  name: 'LangSwitch',
  components: {SvgIcon, ButtonIcon},
  props: {
    lang: {
      /**
       * 'en' | 'zh'
       */
      type: String
    },
    langOptions: {
      /**
       *  label: String
       *  key: 'en' | 'zh'
       */
      type: Array
    },
    showTooltip: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {}
  },
  computed: {
    tooltipContent() {
      if (!this.showTooltip) return '';
      return this.$t('icon.lang');
    }
  },
  methods: {
    changeLang({key}) {
      this.$emit('changeLang', key);
    }
  }
}
</script>
<style scoped></style>
