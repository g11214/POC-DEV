package com.ynet.poc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Tong
 * @date ：Created in 2020/1/16 14:04
 * @description：订单商品状态信息
 * @version: $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("trade_mapping_info")
public class MerStateInfo {
    @TableField("tmi_mer_id")
    private int merId;
    @TableField("tmi_trans_flow_id")
    private String orderId;
    @TableField("tmi_user_id")
    private int userId;
    @TableField("tmi_count")
    private int merCount;
    @TableField("tmi_name")
    private String merName;
    @TableField("tmi_desc")
    private String merDesc;
    @TableField("tmi_expense")
    private double merExpense;
    @TableField("tmi_cover")
    private String merCover;
    @TableField("tmi_stt")
    private String merStt;
}
