package com.luck.cloud.base.vo;

import com.luck.cloud.common.enums.HttpCodeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回结果VO对象
 *
 * @author luck
 * @date 2023-10-26
 */
@Data
@NoArgsConstructor
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code = HttpCodeEnum.OK.getCode();

    private String message;

    private T data;

    public ResultVO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultVO(int code, String message,T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultVO<T> success(T data){
        return new ResultVO<T>(HttpCodeEnum.OK.getCode(), HttpCodeEnum.OK.getMessage(), data);
    }

    public static <T> ResultVO<T> error(T data){
        return new ResultVO<T>(HttpCodeEnum.UN_KNOW_ERROR.getCode(), HttpCodeEnum.UN_KNOW_ERROR.getMessage(), data);
    }

    public ResultVO<T> setCode(int code){
        this.code = code;
        return this;
    }

    public ResultVO<T> setMessage(String message){
        this.message = message;
        return this;
    }

    public ResultVO<T> setData(T data){
        this.data = data;
        return this;
    }

    public ResultVO<T> setErrorCode(String code){
        this.code = HttpCodeEnum.UN_KNOW_ERROR.getCode();
        return this;
    }

    public ResultVO<T> setErrorMessage(String message){
        this.message = HttpCodeEnum.UN_KNOW_ERROR.getMessage();
        return this;
    }

    public ResultVO<T> setSuccessCode(String code){
        this.code = HttpCodeEnum.OK.getCode();
        return this;
    }

    public ResultVO<T> setSuccessMessage(String message){
        this.message = HttpCodeEnum.OK.getMessage();
        return this;
    }

    public boolean isOk(){
        return this.code == HttpCodeEnum.OK.getCode();
    }
}
