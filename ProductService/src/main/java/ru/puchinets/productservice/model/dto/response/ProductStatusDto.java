package ru.puchinets.productservice.model.dto.response;


import lombok.Builder;

import java.util.UUID;
@Builder
public record ProductStatusDto(Long productID, String status, String message, UUID operationID) {
}
