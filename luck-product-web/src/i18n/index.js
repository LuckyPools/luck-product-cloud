import Vue from 'vue';
import VueI18n from 'vue-i18n';
import zh from './lang/zh';
import en from './lang/en';

Vue.use(VueI18n);

export const i18n = new VueI18n({
    locale: localStorage.getItem('locale') || 'zh',
    messages: {
        zh: {
            ...zh // 中文语言包
        },
        en: {
            ...en // 英文语言包
        }
    }
});

export default i18n;

export const $t = i18n.t;
