package com.luck.cloud.auth.enums;

public enum UserStatusCodeEnum {

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

    UserStatusCodeEnum(Integer code, String message) {
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
