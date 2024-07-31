package ru.puchinets.orderservice.service;

public interface KafkaService {
    void sendMessage(String topic, String json);
}
