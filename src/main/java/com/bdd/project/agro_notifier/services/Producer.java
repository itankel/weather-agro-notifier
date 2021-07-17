package com.bdd.project.agro_notifier.services;

import com.bdd.project.agro_notifier.model.AgroNotificationsEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String AGRO_TOPICS="dry_alerts";

    @Autowired
    private KafkaTemplate<String, AgroNotificationsEvents> agroKafkaTemplate;

    public void sendAgroMessage(AgroNotificationsEvents agroNotificationsEvents) {
        logger.info(String.format("#### -> Producing Agro message -> %s", agroNotificationsEvents));
        this.agroKafkaTemplate.send(AGRO_TOPICS, agroNotificationsEvents);
    }

}