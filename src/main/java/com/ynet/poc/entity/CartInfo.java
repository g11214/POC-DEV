package com.ynet.poc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Tong
 * @date ：Created in 2020/1/15 16:48
 * @description：购物车商品
 * @version: $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("trade_cart_info")
public class CartInfo {
    @TableId("tci_id")
    private int cartId;
    @TableField("tci_user_id")
    private int userId;
    @TableField("tci_mer_id")
    private int merId;
    @TableField("tci_count")
    private int merCount;
    @TableField("tci_create_time")
    private String createTime;
    @TableField(exist = false)
    private double orderExpense;
    @TableField(exist = false)
    private String merName;
    @TableField(exist = false)
    private String merDesc;
    @TableField(exist = false)
    private String merCover;
}
