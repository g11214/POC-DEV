package com.ynet.poc.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YnetHttpResponse {

    private String status;

    private String message;
}
