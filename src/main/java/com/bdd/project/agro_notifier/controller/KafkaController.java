package com.bdd.project.agro_notifier.controller;

import com.bdd.project.agro_notifier.model.AgroNotificationsEvents;
import com.bdd.project.agro_notifier.services.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final Producer producer;

    @Autowired
    KafkaController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/agropublish")
    public void sendAgroMessageToKafkaTopic(@RequestParam("usermail") String usermail, @RequestParam("message") String message) {
        this.producer.sendAgroMessage( new AgroNotificationsEvents(usermail,message));
    }


}


