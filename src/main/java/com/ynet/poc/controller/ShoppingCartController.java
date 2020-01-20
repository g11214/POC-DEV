package com.ynet.poc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ynet.poc.common.ResponseVo;
import com.ynet.poc.entity.CartInfo;
import com.ynet.poc.service.CartService;
import com.ynet.poc.service.ProducerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ：Tong
 * @date ：Created in 2020/1/15 8:51
 * @description：购物车
 * @version: $
 */
@RestController
@RequestMapping("/cart")
@Slf4j
@Api(value = "购物车")
public class ShoppingCartController {
    @Autowired
    CartService cartService;
    @Autowired
    ProducerService producerService;

    @ApiOperation("添加一个商品到购物车")
    @PostMapping("/addCartInfo")
    public ResponseVo addCartInfo(CartInfo cartInfo, HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        cartInfo.setUserId(userId);
        cartService.addCartInfo(cartInfo);
        return ResponseVo.SUCCESS();
    }

    @ApiOperation("从购物车删除一个商品")
    @DeleteMapping("/delCartInfo")
    public ResponseVo deleteCartInfo(int cartId) {
        cartService.delCartInfo(cartId);
        return ResponseVo.SUCCESS();
    }

    @ApiOperation("修改购物车商品的数量（走消息队列）")
    @PostMapping("/updateCartCount")
    public ResponseVo updateCartCount(String cartId, String merCount) {
        producerService.updateCartCount(cartId, merCount);
        return ResponseVo.SUCCESS();
    }

    @ApiOperation("更新购物车信息")
    @PutMapping("/updateCartInfo")
    public ResponseVo updateCartInfo(CartInfo cartInfo, HttpServletRequest request) {
        log.info(cartInfo.toString());
        int userId = (int) request.getAttribute("userId");
        cartInfo.setUserId(userId);
        cartService.updateCartInfo(cartInfo);
        return ResponseVo.SUCCESS();
    }

    @ApiOperation("获取购物车列表")
    @GetMapping("/getCartList")
    public ResponseVo getCartList(HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        log.info("获取用户id为" + userId + "的购物车列表");
        List<CartInfo> cartInfos = cartService.getCartList(userId);
        return ResponseVo.SUCCESS().setDataAttribute(cartInfos);
    }
}
