package com.ynet.poc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Tong
 * @date ：Created in 2020/1/15 9:13
 * @description：地址
 * @version: $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("pub_addr_info")
public class AddrInfo {
    @TableId("pai_id")
    private int addrId;
    @TableField("pai_user_mobile")
    private String addrMobile;
    @TableField("pai_name")
    private String addrName;
    @TableField("pai_addr_info")
    private String addrInfo;
    @TableField("pai_user_id")
    private int addrUserId;

    public AddrInfo(String addrMobile, String addrName, String addrInfo, int addrUserId) {
        this(0, addrMobile, addrName, addrInfo, addrUserId);
    }
}
