package ru.puchinets.notificationservice.model.command;

public record TelegramCommand(String email, long chatId) {
}
