package ru.puchinets.notificationservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.puchinets.notificationservice.enums.NotificationProvider;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserData {
    @Id
    private String id;
    private Long userId;
    private String data;
    private NotificationProvider provider;
}
