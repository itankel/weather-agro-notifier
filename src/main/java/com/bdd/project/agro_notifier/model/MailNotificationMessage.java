package com.bdd.project.agro_notifier.model;

import lombok.Builder;

import java.util.List;


@Builder
public class MailNotificationMessage implements  NotificationMessage{

    private List<String> to;
    private String subject;
    private String  text;

    @Override
    public String getMessage() {
        return text;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public String getTarget() {
        return to.get(0);
    }

}
