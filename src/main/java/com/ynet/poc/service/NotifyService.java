package com.ynet.poc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：Tong
 * @date ：Created in 2020/1/20 9:49
 * @description：
 * @version: $
 */
@Service
public class NotifyService {
    @Autowired
    OrderService orderService;

    public void updateOrderInfo(String orderFlowNo, String stt) {
        //支付成功
        orderService.updateOrderStt(orderFlowNo, stt);
    }
}
