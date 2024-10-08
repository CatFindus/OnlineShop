package ru.puchinets.orderservice.model.dto.response;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class OrderCreatedDto {
    private Long orderId;
    private Long userId;
    private String notificationType;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("orderId", orderId)
                .append("userId", userId)
                .append("notificationType", notificationType)
                .toString();
    }
}
