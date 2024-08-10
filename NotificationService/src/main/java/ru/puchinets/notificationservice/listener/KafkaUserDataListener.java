package ru.puchinets.notificationservice.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.puchinets.notificationservice.model.command.UserUpdate;
import ru.puchinets.notificationservice.service.MessageService;
import ru.puchinets.notificationservice.service.UserDataService;

import static ru.puchinets.notificationservice.Constants.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaUserDataListener {

    private final ObjectMapper mapper;
    private final UserDataService userDataService;

    @KafkaListener(topics = "user-updated", groupId = "online-shop")
    public void userUpdatedListener(String message) {
        UserUpdate command = null;
        try {
            command = mapper.readValue(message, UserUpdate.class);
            switch (command.getType()) {
                case CREATE -> userDataService.create(command);
                case UPDATE -> userDataService.update(command);
                case DELETE -> userDataService.delete(command);
            }
        } catch (JsonProcessingException | NullPointerException e) {
            log.warn(KAFKA_DESERIALIZATION_EX, message);
        }
        log.trace(KAFKA_RECEIVE_MESSAGE, message);    }

}
