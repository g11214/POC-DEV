package com.ynet.poc.service;

import com.ynet.poc.entity.MqMessage;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    public void getLoginSmsCode(String mobile) {
        String topic = "sms";
        MqMessage message = new MqMessage(topic, "login", mobile);
        rocketMQTemplate.syncSend(topic, message);
    }

    public void updateCartCount(String cartId, String merCount) {
        String topic = "order";
        MqMessage message = new MqMessage(topic, "", cartId, merCount);
        rocketMQTemplate.syncSend(topic, message);
    }
}
