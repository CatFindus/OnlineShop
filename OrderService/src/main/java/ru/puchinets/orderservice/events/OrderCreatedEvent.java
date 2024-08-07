package ru.puchinets.orderservice.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.puchinets.orderservice.model.entity.Order;

@Getter
public class OrderCreatedEvent extends ApplicationEvent {

    private final Order order;

    public OrderCreatedEvent(Order source) {
        super(source);
        order = source;
    }
}
