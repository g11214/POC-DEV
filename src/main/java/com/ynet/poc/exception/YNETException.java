package com.ynet.poc.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YNETException extends RuntimeException {
    private String errorCode;
    private String errorMessage;
}
