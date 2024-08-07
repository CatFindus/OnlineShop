package ru.puchinets.productservice.model.dto.request;

import lombok.Builder;
import ru.puchinets.productservice.enums.ChangeProductCommand;

import java.util.UUID;

@Builder
public record ChangeProductDto(Long productID, Integer quantity, ChangeProductCommand command, UUID operationID) {
}
