package ru.puchinets.orderservice.model.dto;

import lombok.Builder;
import ru.puchinets.orderservice.enums.ChangeProductCommand;

import java.util.UUID;

@Builder
public record ChangeProductDto(Long productID, Integer quantity, ChangeProductCommand command, UUID operationID) {
}
