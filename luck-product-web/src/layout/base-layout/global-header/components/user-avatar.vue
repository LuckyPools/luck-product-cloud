<template>
  <AButton v-if="!user.isLogin" @click="loginOrRegister">{{ $t('page.login.common.loginOrRegister') }}</AButton>
  <ADropdown v-else placement="bottomRight" :trigger="['click']">
    <ButtonIcon>
      <SvgIcon icon="ph:user-circle" class="text-icon-large"/>
      <span class="text-16px font-medium">{{ user.info.username }}</span>
    </ButtonIcon>
    <template #overlay>
      <AMenu>
        <AMenuItem @click="handleClick">
          <div class="flex-center gap-8px">
            <SvgIcon icon="ph:user-circle" class="text-icon"/>
            {{ $t('common.userCenter') }}
          </div>
        </AMenuItem>
        <AMenuDivider/>
        <AMenuItem @click="logout">
          <div class="flex-center gap-8px">
            <SvgIcon icon="ph:sign-out" class="text-icon"/>
            {{ $t('common.logout') }}
          </div>
        </AMenuItem>
      </AMenu>
    </template>
  </ADropdown>
</template>
<script>
import {Modal} from 'ant-design-vue';
import {mapGetters} from "vuex";
import SvgIcon from "@/component/custom/svg-icon.vue";
import ButtonIcon from "@/component/custom/button-icon.vue";

export default {
  name: 'UserAvatar',
  components: {ButtonIcon, SvgIcon},
  data() {
    return {}
  },
  computed: {
    ...mapGetters(['user'])
  },
  methods: {
    loginOrRegister() {
    },
    logout() {
      Modal.confirm({
        title: this.$t('common.tip'),
        content: this.$t('common.logoutConfirm'),
        okText: this.$t('common.confirm'),
        cancelText: this.$t('common.cancel'),
        onOk: () => {
        }
      });
    },
    handleClick() {
      // todo 切换路由 routerPushByKey(key)
      this.$router.push({name: 'user-center', query: {}})
    }
  }
}

</script>
<style scoped></style>
