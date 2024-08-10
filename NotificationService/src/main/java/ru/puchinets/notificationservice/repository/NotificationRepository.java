package ru.puchinets.notificationservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.puchinets.notificationservice.model.entity.Notification;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
    Page<Notification> findAllByUserId(Long userId, Pageable pageable);
    Page<Notification> findAllByOrderId(Long orderId, Pageable pageable);
}
