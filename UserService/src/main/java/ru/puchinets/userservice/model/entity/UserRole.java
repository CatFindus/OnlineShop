package ru.puchinets.userservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@IdClass(UserRoleId.class)
@Table(name = "user_roles", schema = "user_management")
@Data
@RequiredArgsConstructor
public class UserRole {
    @Id
    private Long userId;
    @Id
    private Integer roleId;
}
