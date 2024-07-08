package ru.puchinets.userservice.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoleId implements Serializable {
    private Long userId;
    private Integer roleId;
}
