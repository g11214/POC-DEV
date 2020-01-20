package com.ynet.poc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：Tong
 * @date ：Created in 2020/1/16 9:35
 * @description：商品
 * @version: $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("trade_mer_info")
public class Merchandise {
    @TableId("tri_id")
    private int merId;
    @TableField("tri_name")
    private String merName;
    @TableField("tri_desc")
    private String merDesc;
    @TableField("tri_expense")
    private double merExpense;
    @TableField("tri_class")
    private String merClass;
    @TableField("tri_cover")
    private String merCover;
    @TableField("tri_comment_count")
    private String merCommnetCount;
    @TableField("tri_rated")
    private String merRated;
}
