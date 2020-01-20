package com.ynet.poc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ynet.poc.entity.MerStateInfo;
import com.ynet.poc.entity.OrderInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface OrderMapper extends BaseMapper<OrderInfo> {
    List<OrderInfo> orderList(@Param("userId") int userId);

    OrderInfo orderInfoById(@Param("orderId") String orderId);
}
