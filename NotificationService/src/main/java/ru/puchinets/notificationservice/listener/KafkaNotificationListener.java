package ru.puchinets.notificationservice.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.puchinets.notificationservice.model.command.OrderNotification;
import ru.puchinets.notificationservice.model.entity.Notification;
import ru.puchinets.notificationservice.model.entity.UserData;
import ru.puchinets.notificationservice.service.NotificationService;
import ru.puchinets.notificationservice.service.UserDataService;
import ru.puchinets.notificationservice.service.MessageService;
import ru.puchinets.notificationservice.util.MessageFabric;

import java.time.LocalDateTime;
import java.util.List;

import static ru.puchinets.notificationservice.Constants.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaNotificationListener {

    private final ObjectMapper mapper;
    private final UserDataService userDataService;
    private final MessageFabric fabric;
    private final NotificationService notificationService;

    @KafkaListener(topics = "order-changed", groupId = "online-shop")
    public void orderCreatedListener(String message) {
        OrderNotification command = null;
        try {
            command = mapper.readValue(message, OrderNotification.class);
            List<UserData> dataList = userDataService.getByUserId(command.userId());
            for (UserData userData : dataList) {

                boolean sent = fabric
                        .service(userData)
                        .sendMessage(String.format(TELEGRAM_ORDER_CREATED_MESSAGE, command.orderId()), userData);
                Notification notification = Notification
                        .builder()
                        .data(userData.getData())
                        .type(command.notificationType())
                        .provider(userData.getProvider())
                        .orderId(command.orderId())
                        .userId(userData.getUserId())
                        .timestamp(LocalDateTime.now())
                        .success(sent)
                        .build();
                notificationService.save(notification);
            }
        } catch (JsonProcessingException | NullPointerException e) {
            log.warn(KAFKA_DESERIALIZATION_EX, message);
        }
        log.trace(KAFKA_RECEIVE_MESSAGE, message);
    }
}
