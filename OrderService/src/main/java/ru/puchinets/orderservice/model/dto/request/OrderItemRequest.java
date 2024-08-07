package ru.puchinets.orderservice.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
}