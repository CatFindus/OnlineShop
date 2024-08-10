package ru.puchinets.notificationservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.puchinets.notificationservice.enums.NotificationProvider;
import ru.puchinets.notificationservice.model.entity.UserData;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDataRepository extends MongoRepository<UserData, String> {
    List<UserData> findAllByUserId(Long userId);
    void deleteAllByUserId(Long userId);
    Optional<UserData> findByUserIdAndProvider(Long userId, NotificationProvider provider);
    Optional<UserData> findByProviderAndData(NotificationProvider provider, String data);
    void deleteUserDataByDataAndProvider(String data, NotificationProvider provider);
}
