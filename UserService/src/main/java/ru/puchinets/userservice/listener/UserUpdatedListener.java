package ru.puchinets.userservice.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.puchinets.userservice.events.UserUpdatedEvent;
import ru.puchinets.userservice.service.KafkaService;

import static ru.puchinets.userservice.Constants.SERIALIZE_MESSGAGE_EX;
import static ru.puchinets.userservice.Constants.USER_UPDATE_KAFKA_TOPIC;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserUpdatedListener {
    private final KafkaService kafkaService;
    private final ObjectMapper mapper;

    @EventListener
    public void statusUpdated(UserUpdatedEvent event) {
        String message = null;
        try {
            message = mapper.writeValueAsString(event.getData());
        } catch (JsonProcessingException e) {
            log.warn(SERIALIZE_MESSGAGE_EX, event.getData(), e.getMessage());
        }
        kafkaService.sendMessage(USER_UPDATE_KAFKA_TOPIC, message);
    }

}
