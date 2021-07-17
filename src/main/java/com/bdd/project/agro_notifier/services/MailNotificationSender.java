package com.bdd.project.agro_notifier.services;

import com.bdd.project.agro_notifier.model.NotificationMessage;
import com.bdd.project.agro_notifier.model.NotificationsAdaptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailNotificationSender implements NotificationSender{
    private static Logger LOGGER = LoggerFactory.getLogger(MailNotificationSender.class);

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMessage(NotificationMessage notificationMessage) {
        LOGGER.debug("start of sendMessage");
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(notificationMessage.getTarget());
        msg.setSubject(notificationMessage.getSubject());
        msg.setText(notificationMessage.getMessage());
        javaMailSender.send(msg);
        LOGGER.debug("end of sendMessage");
    }

}
