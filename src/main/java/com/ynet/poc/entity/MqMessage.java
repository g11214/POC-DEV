package com.ynet.poc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MqMessage {

    private String topic;
    private String tag;
    private String key;
    private Object message;
    private Date createTime;
    private boolean isConsumed;
    private Date consumedTime;

    public MqMessage(String topic, String tag, Object message) {
        this(topic, tag, null, message, new Date(), false, null);
    }

    public MqMessage(String topic, String tag, String key, Object message) {
        this(topic, tag, key, message, new Date(), false, null);
    }

    public MqMessage(String topic, Object message) {
        this(topic, null, null, message, new Date(), false, null);
    }
}
