package com.bdd.project.agro_notifier.model;


public class NotificationsAdaptor implements NotificationMessage {
    private AgroNotificationsEvents agroNotificationsEvents;
    private static final String ALERT_ON="Alert on ";

    public NotificationsAdaptor(AgroNotificationsEvents agroNotificationsEvents){
        this.agroNotificationsEvents=agroNotificationsEvents;
    }

    @Override
    public String getMessage() {
        return ALERT_ON +agroNotificationsEvents.getCropName();
    }

    @Override
    public String getSubject() {
        return ALERT_ON+ agroNotificationsEvents.getCropName();
    }

    @Override
    public String getTarget() {
        return agroNotificationsEvents.getNotificationTo();
    }

}
