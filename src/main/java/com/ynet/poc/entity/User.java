package com.ynet.poc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("platform_user_reg")
public class User {
    private int userId;

    private String userName;

    private String userMobile;

    private String userStt;

    private String userLastLoginTime;

    private String userRegistTime;

}
