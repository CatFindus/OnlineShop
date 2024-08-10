package ru.puchinets.notificationservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.puchinets.notificationservice.enums.NotificationProvider;
import ru.puchinets.notificationservice.enums.NotificationType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document("notification")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    @Id
    private String id;
    private String data;
    private Long userId;
    private Long orderId;
    private boolean success;
    private LocalDateTime timestamp;
    private NotificationType type;
    private NotificationProvider provider;
}
