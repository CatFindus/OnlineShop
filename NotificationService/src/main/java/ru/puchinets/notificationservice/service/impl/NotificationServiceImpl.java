package ru.puchinets.notificationservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.puchinets.notificationservice.model.entity.Notification;
import ru.puchinets.notificationservice.repository.NotificationRepository;
import ru.puchinets.notificationservice.service.NotificationService;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;

    @Override
    public Page<Notification> getNotificationByUserId(Long userId, Pageable pageable) {
        return repository.findAllByUserId(userId, pageable);
    }

    @Override
    public Page<Notification> getNotificationByOrderId(Long orderId, Pageable pageable) {
        return repository.findAllByOrderId(orderId, pageable);
    }

    @Override
    public Notification save(Notification notification) {
        return repository.save(notification);
    }
}
