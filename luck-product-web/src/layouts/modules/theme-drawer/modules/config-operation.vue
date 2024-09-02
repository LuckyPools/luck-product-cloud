<template>
  <div class="flex justify-between bg-drawer">
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
    return {}
  },
  computed: {
    ...mapGetters(['theme']),
  },
  mounted() {
    this.initClipboard();
  },
  methods: {
    initClipboard() {
      let that = this;
      const domRef = this.$refs.domRef;
      if (!domRef) return;

      const clipboard = new Clipboard(domRef, {
        text: () => this.getClipboardText()
      });

      clipboard.on('success', () => {
          that.$message?.success(that.$t('theme.configOperation.copySuccessMsg'));
      });
    },

    getClipboardText() {
      const reg = /"\w+":/g;
      const json = JSON.stringify(this.theme);
      return json.replace(reg, match => match.replace(/"/g, ''));
    },

    handleReset() {
      let that = this;
      this.$store.dispatch('theme/reset').then(() => {
        setTimeout(() => {
            that.$message?.success(that.$t('theme.configOperation.resetSuccessMsg'));
        }, 50);
      });

    }
  }
}
</script>

<style scoped></style>
