package com.bdd.project.agro_notifier.services;

import com.bdd.project.agro_notifier.model.NotificationMessage;

public interface NotificationSender {
     void sendMessage(NotificationMessage notificationMessage);
}
