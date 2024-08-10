package ru.puchinets.userservice.model.command;

import lombok.Builder;
import lombok.Data;
import ru.puchinets.userservice.enums.UserUpdateCommandType;

@Data
@Builder
public class UserUpdate {
    private Long userId;
    private String email;
    private String phone;
    private UserUpdateCommandType type;


}
