package ru.puchinets.notificationservice.model.command;


import ru.puchinets.notificationservice.enums.NotificationType;

public record OrderNotification(Long orderId, Long userId, NotificationType notificationType) {
}
