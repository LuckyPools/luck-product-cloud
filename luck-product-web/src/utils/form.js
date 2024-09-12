import {REG_CODE_SIX, REG_EMAIL, REG_PHONE, REG_PWD, REG_USER_NAME} from '@/constants/reg';
import {$t} from '@/locales';
import {toValue} from "@vueuse/core";

export function useFormRules() {
  const patternRules = {
    userName: {
      pattern: REG_USER_NAME,
      message: $t('form.userName.invalid'),
      trigger: 'change'
    },
    phone: {
      pattern: REG_PHONE,
      message: $t('form.phone.invalid'),
      trigger: 'change'
    },
    pwd: {
      pattern: REG_PWD,
      message: $t('form.pwd.invalid'),
      trigger: 'change'
    },
    code: {
      pattern: REG_CODE_SIX,
      message: $t('form.code.invalid'),
      trigger: 'change'
    },
    email: {
      pattern: REG_EMAIL,
      message: $t('form.email.invalid'),
      trigger: 'change'
    }
  };

  const formRules = {
    userName: [createRequiredRule($t('form.userName.required')), patternRules.userName],
    phone: [createRequiredRule($t('form.phone.required')), patternRules.phone],
    pwd: [createRequiredRule($t('form.pwd.required')), patternRules.pwd],
    code: [createRequiredRule($t('form.code.required')), patternRules.code],
    email: [createRequiredRule($t('form.email.required')), patternRules.email]
  };

  /** the default required rule */
  const defaultRequiredRule = createRequiredRule($t('form.required'));

  function createRequiredRule(message) {
    return {
      required: true,
      message
    };
  }

  /** create a rule for confirming the password */
  function createConfirmPwdRule(pwd) {
    return [
      {required: true, message: $t('form.confirmPwd.required')},
      {
        validator: (rule, value) => {
          if (value.trim() !== '' && value !== toValue(pwd)) {
            return Promise.reject(rule.message);
          }
          return Promise.resolve();
        },
        message: $t('form.confirmPwd.invalid'),
        trigger: 'change'
      }
    ];
  }

  return {
    patternRules,
    formRules,
    defaultRequiredRule,
    createRequiredRule,
    createConfirmPwdRule
  };
}