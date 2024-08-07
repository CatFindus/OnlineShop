package ru.puchinets.orderservice.listeners;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.puchinets.orderservice.events.OrderCreatedEvent;
import ru.puchinets.orderservice.model.dto.response.OrderCreatedDto;
import ru.puchinets.orderservice.service.KafkaService;

import static ru.puchinets.orderservice.Constants.*;

@Component
@RequiredArgsConstructor
public class OrderCreatedListener {
    private final KafkaService kafkaService;

    @EventListener
    public void statusUpdated(OrderCreatedEvent event) {
        var orderCreatedDto = new OrderCreatedDto();
        orderCreatedDto.setOrderId(event.getOrder().getId());
        orderCreatedDto.setNotificationType(ORDER_CREATED_NOTIFICATION_TYPE);
        orderCreatedDto.setUserId(event.getOrder().getUserId());
        kafkaService.sendMessage(ORDER_CREATED_KAFKA_TOPIC, orderCreatedDto.toString());
    }

}
