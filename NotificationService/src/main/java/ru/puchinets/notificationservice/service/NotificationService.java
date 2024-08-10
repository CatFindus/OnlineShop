package ru.puchinets.notificationservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.puchinets.notificationservice.model.entity.Notification;

public interface NotificationService {
    Page<Notification> getNotificationByUserId(Long userId, Pageable pageable);
    Page<Notification> getNotificationByOrderId(Long orderId, Pageable pageable);
    Notification save(Notification notification);
}
