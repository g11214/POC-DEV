package com.ynet.poc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsCode {

    private String mobile;
    private String code;
    private Date createTime;
    private Date expireTime;

    public SmsCode(String mobile, String code, Date expireTime) {
        this(mobile, code, new Date(), expireTime);
    }

}
