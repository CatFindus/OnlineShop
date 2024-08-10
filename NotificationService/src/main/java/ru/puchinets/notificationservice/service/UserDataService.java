package ru.puchinets.notificationservice.service;

import ru.puchinets.notificationservice.enums.NotificationProvider;
import ru.puchinets.notificationservice.model.command.UserUpdate;
import ru.puchinets.notificationservice.model.entity.UserData;

import java.util.List;
import java.util.Optional;

public interface UserDataService {
    List<UserData> create(UserUpdate userUpdate);
    UserData save(UserData data);
    boolean delete(UserUpdate userUpdate);
    List<UserData> update(UserUpdate userUpdate);
    List<UserData> getByUserId(Long userId);
    Optional<UserData> findByEmail(String email);
    void deleteUserData(String data, NotificationProvider provider);
}
