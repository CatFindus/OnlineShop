package ru.puchinets.notificationservice.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.puchinets.notificationservice.config.TelegramBotConfig;
import ru.puchinets.notificationservice.enums.NotificationProvider;
import ru.puchinets.notificationservice.events.TelegramAddChatEvent;
import ru.puchinets.notificationservice.events.TelegramDeleteChatEvent;
import ru.puchinets.notificationservice.model.entity.UserData;
import ru.puchinets.notificationservice.service.UserDataService;

import java.util.Optional;

import static ru.puchinets.notificationservice.Constants.CHAT_REGISTERED;
import static ru.puchinets.notificationservice.Constants.USER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class TelegramChatChangeListener {
    private final UserDataService service;
    private final TelegramBotConfig config;

    @EventListener
    public void addChatListener(TelegramAddChatEvent event) {
        Optional<UserData> mayBeUserData = service.findByEmail(event.getCommand().email());
        UserData tgChat = new UserData(null,null, Long.toString(event.getCommand().chatId()), NotificationProvider.TELEGRAM);
        if (mayBeUserData.isEmpty()) {
            config.sendCustomMessage(event.getCommand().chatId(), String.format(USER_NOT_FOUND, event.getCommand().email()));
        } else {
            tgChat.setUserId(mayBeUserData.get().getUserId());
            service.save(tgChat);
            config.sendCustomMessage(event.getCommand().chatId(), CHAT_REGISTERED);
            config.registerChat(event.getCommand().chatId());
        }
    }

    @EventListener
    public void deleteChatListener(TelegramDeleteChatEvent event) {
        service.deleteUserData(String.valueOf(event.getCommand().chatId()), NotificationProvider.TELEGRAM);
    }
}
