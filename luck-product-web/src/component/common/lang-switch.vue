
<template>
  <a-dropdown placement="bottomCenter">
    <ButtonIcon :tooltip-content="tooltipContent" tooltip-placement="left">
      <SvgIcon icon="heroicons:language" />
    </ButtonIcon>
    <template #overlay>
      <AMenu :selected-keys="[lang]">
        <AMenuItem v-for="option in langOptions" :key="option.key" @click="changeLang(option.key)">
          {{ option.label }}
        </AMenuItem>
      </AMenu>
    </template>
  </a-dropdown>
</template>
<script>
import ButtonIcon from "@/component/custom/button-icon.vue";
import SvgIcon from "@/component/custom/svg-icon.vue";

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
  computed:{
    tooltipContent(){
      if (!this.showTooltip) return '';
      return this.$t('icon.lang');
    }
  },
  methods: {
    changeLang(lang) {
      this.$emit('changeLang', lang);
    }
  }
}
</script>
<style scoped></style>
