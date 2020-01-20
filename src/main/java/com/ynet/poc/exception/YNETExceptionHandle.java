package com.ynet.poc.exception;

import com.ynet.poc.common.YnetHttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class YNETExceptionHandle {
    @ExceptionHandler(YNETException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public YnetHttpResponse handlerLeException(YNETException ex) {
        return new YnetHttpResponse(ex.getErrorCode(), ex.getErrorMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public YnetHttpResponse handlerLeException(Exception ex) {
        return new YnetHttpResponse("404", ex.getMessage());
    }
}
