package com.ynet.poc.controller;

import com.ynet.poc.common.ResponseVo;
import com.ynet.poc.service.NotifyService;
import com.ynet.poc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Tong
 * @date ：Created in 2020/1/15 16:12
 * @description：支付回调
 * @version: $
 */
@Slf4j
@RestController
@RequestMapping("/payNotify")
public class NotifyController {
    @Autowired
    NotifyService notifyService;

    /**
     * @Description: TODO
     * @Param: [reCode, reMsg, stt, orderFlowNo, createTime, bsnCode]
     * @Return: void
     * @Date: 2020/1/15
     **/
    @PostMapping
    public ResponseVo getNotifyInfo(String reCode, String reMsg, String stt, String orderFlowNo, String createTime, String bsnCode) {
        //返回成功
        if ("AA".equals(reCode) && "payCallBack".equals(bsnCode)) {
            notifyService.updateOrderInfo(orderFlowNo, stt);
        }
        return ResponseVo.SUCCESS();
    }
}
