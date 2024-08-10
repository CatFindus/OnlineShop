package ru.puchinets.notificationservice.service;

import ru.puchinets.notificationservice.model.entity.UserData;

public interface MessageService {

    boolean sendMessage(String message, UserData userData);

}
