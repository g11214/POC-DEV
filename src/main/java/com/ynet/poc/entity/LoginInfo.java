package com.ynet.poc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("platform_user_reg")
public class LoginInfo {
    @TableId("pur_user_id")
    private int userId;
    @TableField("pur_userName")
    private String userName;
    @TableField("pur_mobile_no")
    private String mobile;
    @TableField(exist = false)
    private String smsCode;
    @TableField(exist = false)
    private int type;


    public LoginInfo(String mobile) {
        this(0, null, mobile, "", 1);
    }

}
