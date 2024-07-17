package com.luck.cloud.common.enums;

/**
 * 登录状态返回码
 * @author luck
 * @date 2024-07-17
 */
public enum AuthCodeEnum {

    ACCOUNT_NOT_EXIST(0,"用户不存在"),
    LOGIN_PASSWORD_ERROR(1,"密码错误"),
    ACCOUNT_EXPIRED(2,"过期"),
    ACCOUNT_LOCKED(3,"锁定"),
    ACCOUNT_CREDENTIAL_EXPIRED(4,"凭证过期错误"),
    ACCOUNT_DISABLE(5,"删除"),
    USER_UNAUTHORIZED(6,"用户未授权"),
    PERMISSION_DENIED(7,"权限不足");

    private Integer code;
    private String message;

    AuthCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
