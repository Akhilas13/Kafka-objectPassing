package com.stackroute.kafka.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class Producer {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(Producer.class);

    @Value("${kafka.topic.json}")
    private String jsonTopic;

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    public void send(User user) {
        LOGGER.info("sending user='{}'", user.toString());
        kafkaTemplate.send(jsonTopic, user);
    }
}