package com.luck.cloud.common.enums;

/**
 * http状态返回码
 *
 * @author luck
 * @date 2023-10-26
 */
public enum HttpCodeEnum {
    OK(200, "操作成功"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    INTERNAL_SERVER_ERROR(500, "服务器发生错误"),
    UN_KNOW_ERROR(500, "未知错误");

    private final int code;
    private final String message;

    HttpCodeEnum(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int  getCode() {
        return code;
    }

}
