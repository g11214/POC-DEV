package com.ynet.poc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @author ：Tong
 * @date ：Created in 2020/1/15 16:48
 * @description：订单信息
 * @version: $
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("trade_trans_info")
public class OrderInfo {
    @TableField("tti_flow_id")
    private String orderId;
    @TableField("tti_user_id")
    private int userId;
    @TableField("tti_stt")
    private int orderStt;
    @TableField("tti_create_time")
    private String orderCreateTime;
    @TableField("tti_addr_id")
    private int orderAddr;
    @TableField("tti_expense")
    private double orderExpense;
    @TableField(exist = false)
    private ArrayList<MerStateInfo> merStateInfos;

    public void addMerInfo(MerStateInfo merStateInfo) {
        merStateInfos.add(merStateInfo);
    }
}
