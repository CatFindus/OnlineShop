package ru.puchinets.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.puchinets.userservice.service.KafkaService;

import static ru.puchinets.userservice.Constants.KAFKA_SEND_MESSAGE;


@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessage(String topic, String json) {
        kafkaTemplate.send(topic, json);
        log.info(KAFKA_SEND_MESSAGE, topic, json);
    }
}
