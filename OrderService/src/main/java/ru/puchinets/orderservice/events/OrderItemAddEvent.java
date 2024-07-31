package ru.puchinets.orderservice.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.puchinets.orderservice.model.dto.response.OrderItemResponse;

@Getter
public class OrderItemAddEvent extends ApplicationEvent {

    private final OrderItemResponse orderItem;

    public OrderItemAddEvent(OrderItemResponse source) {
        super(source);
        orderItem = source;

    }
}
