package com.luck.cloud.auth.enums;

public enum UserStatusCodeEnum {

    ACCOUNT_NOT_EXIST(0,"正常"),
    LOGIN_PASSWORD_ERROR(1,"禁用"),
    ACCOUNT_EXPIRED(2,"过期"),
    ACCOUNT_LOCKED(3,"锁定"),
    ACCOUNT_DISABLED(4,"禁用"),
    ACCOUNT_CREDENTIAL_EXPIRED(5,"凭证过期错误"),
    ACCOUNT_DISABLE(6,"删除"),
    USER_UNAUTHORIZED(7,"用户未授权"),
    PERMISSION_DENIED(8,"权限不足");

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
