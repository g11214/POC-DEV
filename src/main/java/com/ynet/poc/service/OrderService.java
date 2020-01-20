package com.ynet.poc.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ynet.poc.entity.CartInfo;
import com.ynet.poc.entity.MerStateInfo;
import com.ynet.poc.entity.Merchandise;
import com.ynet.poc.entity.OrderInfo;
import com.ynet.poc.mapper.OrderMapper;
import com.ynet.poc.util.HttpClient;
import com.ynet.poc.util.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ：Tong
 * @date ：Created in 2020/1/16 17:02
 * @description：
 * @version: $
 */

@Service
@Slf4j
@Transactional
public class OrderService extends ServiceImpl<OrderMapper, OrderInfo> {
    @Autowired
    MerStateService merStateService;
    @Autowired
    MerchandiseService merchandiseService;
    @Autowired
    CartService cartService;

    public String addOrderInfo(List<CartInfo> cartInfos, int userId, int addrId) {
        //获取商品信息列表
//        List<Merchandise> merchandises = merchandiseService.getMerListById(cartInfos);
        //获取序列号
        SnowFlake snowFlake = new SnowFlake(1, 1);

        String orderId = "Ord" + snowFlake.nextId();
        log.info(orderId);

        double expense = 0;
        //插入订单商品映射表
        for (CartInfo cartInfo : cartInfos) {
            //如果从购物车来的，生成订单前删除购物车中的信息s
            if (cartInfo.getCartId() != 0) {
                cartService.delCartInfo(cartInfo.getCartId());
            }
            MerStateInfo merStateInfo = new MerStateInfo(cartInfo.getMerId(), orderId, userId,
                    cartInfo.getMerCount(), cartInfo.getMerName(), cartInfo.getMerDesc(),
                    cartInfo.getOrderExpense(), cartInfo.getMerCover(), "50");
            expense += cartInfo.getOrderExpense() * cartInfo.getMerCount();
            merStateService.save(merStateInfo);
        }
        //插入订单信息表
        OrderInfo orderInfo = new OrderInfo(orderId, userId, 50, null, addrId, expense, null);
        log.info(orderInfo.toString());
        this.save(orderInfo);

        return orderId;
    }

    /**
     * @Description: 修改订单状态
     * @Param: [orderId, orderStt]
     * @Return: void
     * @Date: 2020/1/17
     **/
    public void updateOrderStt(String orderId, String orderStt) {
        UpdateWrapper<OrderInfo> wrapper = new UpdateWrapper<>();
        wrapper.eq("tti_flow_id", orderId).set("tti_stt", orderStt);
        this.update(wrapper);
    }

    public OrderInfo getOrderInfo(String orderId) {
        return this.getById(orderId);
    }

    public List<OrderInfo> getOrderList(int userId) {
        return this.baseMapper.orderList(userId);
    }
}
