package com.bdd.project.agro_notifier.services;

import com.bdd.project.agro_notifier.model.AgroNotificationsEvents;
import com.bdd.project.agro_notifier.model.NotificationsAdaptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class EventsLoader {

    private final Logger logger = LoggerFactory.getLogger(EventsLoader.class);
    private NotificationSender notificationSender;

    EventsLoader(NotificationSender notificationSender){
        this.notificationSender=notificationSender;
    }


    @KafkaListener(
            //topics = "dry_alerts",
            topics={"dry_alerts","cool_alerts","heat_alerts"},
            groupId = "agro_group_id",
            containerFactory = "AgroKafkaListenerContainerFactory")
    void listener(AgroNotificationsEvents agroNotificationsEvents) {
        logger.info("CustomAgroEventListener [{}]", agroNotificationsEvents);
        notificationSender.sendMessage(new NotificationsAdaptor(agroNotificationsEvents));
    }

}
