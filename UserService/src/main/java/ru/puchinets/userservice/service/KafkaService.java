package ru.puchinets.userservice.service;

public interface KafkaService {
    void sendMessage(String topic, String json);
}
