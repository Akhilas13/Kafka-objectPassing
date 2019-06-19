package com.stackroute.kafka.controller;

import com.stackroute.kafka.domain.User;
import com.stackroute.kafka.domain.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
//@CrossOrigin("*")
@RequestMapping("/api/v1")
public class ProducerController {

    @Autowired
    Producer producer;

    @PostMapping("/produce")
    public String produceData(@RequestBody User user) {

        System.out.println("REQUEST BODY..!! "+ user);

        producer.send(user);

        return "Produced to Kafka Topic !!";

    }
}
