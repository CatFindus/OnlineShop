package ru.puchinets.orderservice.model.dto;


import java.util.UUID;

public record ProductStatusDto(Long productID, String status, String message, UUID operationID) {
}
