package com.ynet.poc.controller;

import com.alibaba.fastjson.JSON;
import com.ynet.poc.common.ResponseVo;
import com.ynet.poc.entity.CartInfo;
import com.ynet.poc.entity.OrderInfo;
import com.ynet.poc.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：Tong
 * @date ：Created in 2020/1/15 8:52
 * @description：下单
 * @version: $
 */
@RestController
@RequestMapping("/order")
@Api(value = "下单")
@Slf4j
public class OrderController {
    @Autowired
    OrderService orderService;

    @PutMapping("/addOrderInfo")
    @ApiOperation("新增订单信息")

    public ResponseVo addOrderInfo(@RequestBody List<CartInfo> cartInfos, int addrId, HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        log.info(Arrays.toString(cartInfos.toArray()));
        String orderId = orderService.addOrderInfo(cartInfos, userId, addrId);
        return ResponseVo.SUCCESS().setDataAttribute(orderId);
    }

    @GetMapping
    @ApiOperation("获取订单信息")
    public ResponseVo getOrderInfo(String orderId) {
        OrderInfo orderInfo = orderService.getOrderInfo(orderId);
        return ResponseVo.SUCCESS().setDataAttribute(orderInfo);
    }

    @GetMapping("/getOrderList")
    @ApiOperation("获取订单列表")
    public ResponseVo getOrderList(HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        List<OrderInfo> orderInfos = orderService.getOrderList(userId);
        return ResponseVo.SUCCESS().setDataAttribute(orderInfos);
    }
}
