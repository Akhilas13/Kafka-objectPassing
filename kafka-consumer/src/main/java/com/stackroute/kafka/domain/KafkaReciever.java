package com.stackroute.kafka.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

public class KafkaReciever {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(KafkaReciever.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "${kafka.topic.json}")
    public void receive(User user) {
        System.out.println(user.toString());
        LOGGER.info("received user='{}'", user.toString());
        latch.countDown();
    }
}
