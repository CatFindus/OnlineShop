package ru.puchinets.orderservice.listeners;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.puchinets.orderservice.events.OrderItemAddEvent;
import ru.puchinets.orderservice.service.KafkaService;

import static ru.puchinets.orderservice.Constants.*;

@Component
@RequiredArgsConstructor
public class OrderLivecycleListener {

    private final KafkaService kafkaService;

    @EventListener
    public void statusUpdated(OrderItemAddEvent event) {
        kafkaService.sendMessage(ORDER_ITEM_LIFECYCLE_KAFKA_TOPIC, event.getOrderItem().toString());
    }

}
