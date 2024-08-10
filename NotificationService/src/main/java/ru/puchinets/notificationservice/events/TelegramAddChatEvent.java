package ru.puchinets.notificationservice.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.puchinets.notificationservice.model.command.TelegramCommand;

@Getter
public class TelegramAddChatEvent extends ApplicationEvent {

    private final TelegramCommand command;


    public TelegramAddChatEvent(TelegramCommand source) {
        super(source);
        command = source;
    }
}
