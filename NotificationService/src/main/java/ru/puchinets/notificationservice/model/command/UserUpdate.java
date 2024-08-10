package ru.puchinets.notificationservice.model.command;

import lombok.Builder;
import lombok.Data;
import ru.puchinets.notificationservice.enums.UserUpdateCommandType;

@Data
@Builder
public class UserUpdate {
    private Long userId;
    private String email;
    private String phone;
    private UserUpdateCommandType type;


}
