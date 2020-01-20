package com.ynet.poc.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVo extends YnetHttpResponse {

    private Object data;

    public ResponseVo(String status, String message) {
        super(status, message);
    }

    public static ResponseVo SUCCESS() {
        return new ResponseVo(Constants.HTTP_RESPONSE_STATUS_SUCCESS_DEFAULT_CODE,
                Constants.HTTP_RESPONSE_STATUS_SUCCESS_DEFAULT_MESSAGE);
    }

    public static ResponseVo SUCCESS(String message) {
        return new ResponseVo(Constants.HTTP_RESPONSE_STATUS_SUCCESS_DEFAULT_CODE, message);
    }

    public static ResponseVo SUCCESS(String status, String message) {
        return new ResponseVo(status, message);
    }

    public static ResponseVo FAIL() {
        return new ResponseVo(Constants.HTTP_RESPONSE_STATUS_FAIL_DEFAULT_CODE,
                Constants.HTTP_RESPONSE_STATUS_FAIL_DEFAULT_MESSAGE);
    }

    public static ResponseVo FAIL(String message) {
        return new ResponseVo(Constants.HTTP_RESPONSE_STATUS_FAIL_DEFAULT_CODE, message);
    }

    public static ResponseVo FAIL(String status, String message) {
        return new ResponseVo(status, message);
    }

    public ResponseVo setDataAttribute(Object data) {
        this.setData(data);
        return this;
    }
}
