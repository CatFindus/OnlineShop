package ru.puchinets.userservice.model.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserResponse implements Serializable {
    private Long id;
    private String username;
    private String email;
    private String firstname;
    private String lastName;
    private LocalDate birthDate;
    private String phone;
    private LocalDateTime lastLogin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
