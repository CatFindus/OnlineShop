package ru.puchinets.orderservice.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.puchinets.orderservice.model.entity.OrderStatus;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long userId;
    private OrderStatus status;
    private BigDecimal totalPrice;
}
