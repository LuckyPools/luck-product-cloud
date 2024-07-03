package com.luck.cloud.base.vo;

import com.luck.cloud.common.enums.HttpCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回结果VO对象
 *
 * @author luck
 * @date 2023-10-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {

    private String code;

    private String message;

    private T data;

    public ResultVO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultVO(String code, String message,T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultVO<T> success(T data){
        return new ResultVO(HttpCodeEnum.OK.getCode(), HttpCodeEnum.OK.getMessage(), data);
    }

    public static <T> ResultVO<T> error(T data){
        return new ResultVO(HttpCodeEnum.UN_KNOW_ERROR.getCode(), HttpCodeEnum.UN_KNOW_ERROR.getMessage(), data);
    }

}
