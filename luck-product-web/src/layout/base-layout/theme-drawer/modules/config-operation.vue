<template>
  <div class="flex justify-between">
    <AButton danger @click="handleReset">{{ $t('theme.configOperation.resetConfig') }}</AButton>
    <div ref="domRef">
      <AButton type="primary">{{ $t('theme.configOperation.copyConfig') }}</AButton>
    </div>
  </div>
</template>
<script>
import Clipboard from 'clipboard';
import {mapGetters} from "vuex";

export default {
  name: 'ConfigOperation',
  data() {
    return {
      domRef: null
    }
  },
  computed: {
    ...mapGetters(['theme']),
  },
  mounted() {
    this.initClipboard();
  },
  methods: {
    initClipboard() {
      if (!this.domRef) return;

      const clipboard = new Clipboard(this.domRef, {
        text: () => this.getClipboardText()
      });

      clipboard.on('success', () => {
        window.$message?.success(this.$t('theme.configOperation.copySuccessMsg'));
      });
    },

    getClipboardText() {
      const reg = /"\w+":/g;
      const json = JSON.stringify(this.theme);
      return json.replace(reg, match => match.replace(/"/g, ''));
    },

    handleReset() {
      this.$store.dispatch('theme/reset').then(() => {
        setTimeout(() => {
          this.$message?.success(this.$t('theme.configOperation.resetSuccessMsg'),1000);
        }, 50);
      });

    }
  }
}
</script>

<style scoped></style>
