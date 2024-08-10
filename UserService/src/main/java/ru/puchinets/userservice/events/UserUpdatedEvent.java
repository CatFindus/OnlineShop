package ru.puchinets.userservice.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.puchinets.userservice.model.command.UserUpdate;

@Getter
public class UserUpdatedEvent extends ApplicationEvent {

    private final UserUpdate data;

    public UserUpdatedEvent(UserUpdate source) {
        super(source);
        data = source;
    }
}
