package com.ynet.poc.service;

import com.ynet.poc.entity.MqMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageService {

    @Autowired
    LoginService loginService;

    @Autowired
    CartService cartService;

    public void doHandleLoginMessage(MqMessage message) {
        String mobile = (String) message.getMessage();
        log.info(mobile);
        loginService.getSmsCode(mobile);
    }

    public void doHandleOrderMessage(MqMessage message) {
        int cartId = Integer.parseInt(message.getKey());
        int merCount = Integer.parseInt((String) message.getMessage());
        log.info("购物车id为：" + cartId + "修改成数量为：" + merCount);
        cartService.updateMerCount(cartId, merCount);
    }
}
