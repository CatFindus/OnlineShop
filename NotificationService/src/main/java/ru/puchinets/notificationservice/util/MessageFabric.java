package ru.puchinets.notificationservice.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.puchinets.notificationservice.model.entity.UserData;
import ru.puchinets.notificationservice.service.MessageService;

import static ru.puchinets.notificationservice.enums.NotificationProvider.SMS;

@Component
@RequiredArgsConstructor
public class MessageFabric {

    private final MessageService telegramMessageServiceImpl;

    public MessageService service(UserData data) {
        switch (data.getProvider())
        {
            case TELEGRAM -> { return telegramMessageServiceImpl; }
            case SMS -> { return null; }
            case MAIL -> { return null; }
            default -> throw new IllegalArgumentException();
        }
    }
}
