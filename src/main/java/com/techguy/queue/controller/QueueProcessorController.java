package com.techguy.queue.controller;

import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueueProcessorController {

    @Autowired
    SqsTemplate sqsTemplate;

    @GetMapping("send/{message}")
    public void send(@PathVariable String message) {
        sqsTemplate.send("https://sqs.us-east-2.amazonaws.com/857597739256/queueGrp", message);
    }

    @SqsListener("queueGrp")
    public void receive(String message) {
        System.out.println(message);
    }
}
