package ru.puchinets.notificationservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.puchinets.notificationservice.config.TelegramBotConfig;
import ru.puchinets.notificationservice.model.entity.UserData;
import ru.puchinets.notificationservice.service.MessageService;

@Service
@RequiredArgsConstructor
public class TelegramMessageServiceImpl implements MessageService {

    private final TelegramBotConfig bot;

    @Override
    public boolean sendMessage(String message, UserData userData) {
//        Long chatId = 468498775L;
        return bot.sendCustomMessage(Long.parseLong(userData.getData()),message);
    }
}
