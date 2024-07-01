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
public class ResultVO {

    private String code;

    private String message;

    private Object data;

    public ResultVO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultVO success(Object data) {
        return new ResultVO(HttpCodeEnum.OK.getCode(), HttpCodeEnum.OK.getMessage(), data);
    }

    public static ResultVO error() {
        return new ResultVO(HttpCodeEnum.UN_KNOW_ERROR.getCode(), HttpCodeEnum.UN_KNOW_ERROR.getMessage());
    }
}
