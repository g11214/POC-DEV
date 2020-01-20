package com.ynet.poc.listener;

import com.alibaba.fastjson.JSON;
import com.ynet.poc.entity.MqMessage;
import com.ynet.poc.service.MessageService;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RocketMQMessageListener(
        topic = "order",
        consumerGroup = "orderConsu")
public class OrderListener implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {

    @Autowired
    MessageService messageService;

    @Override
    public void onMessage(MessageExt message) {
        MqMessage mqMessage = JSON.parseObject(new String(message.getBody()), MqMessage.class);
        if (!mqMessage.isConsumed()) {
            messageService.doHandleOrderMessage(mqMessage);
            mqMessage.setConsumed(true);
            mqMessage.setConsumedTime(new Date());
        }
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {

    }
}
