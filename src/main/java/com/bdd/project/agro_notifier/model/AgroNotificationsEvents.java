package com.bdd.project.agro_notifier.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AgroNotificationsEvents implements Serializable {
    private String notificationTo;
    private String cropName;
}
