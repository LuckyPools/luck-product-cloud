package com.luck.cloud.common.exception;

/**
 * 数据非唯一异常
 *
 * @author luck
 * @date 2024-07-02
 */
public class NotUniqueDataException extends RuntimeException {

    public NotUniqueDataException(String msg) {
        super(msg);
    }

    public NotUniqueDataException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
