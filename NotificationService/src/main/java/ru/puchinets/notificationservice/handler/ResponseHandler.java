package ru.puchinets.notificationservice.handler;

import org.mapdb.DBException;
import org.springframework.context.ApplicationEventPublisher;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.puchinets.notificationservice.enums.UserState;
import ru.puchinets.notificationservice.events.TelegramAddChatEvent;
import ru.puchinets.notificationservice.events.TelegramDeleteChatEvent;
import ru.puchinets.notificationservice.model.command.TelegramCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static ru.puchinets.notificationservice.Constants.*;
import static ru.puchinets.notificationservice.enums.UserState.AWAITING_EMAIL;
import static ru.puchinets.notificationservice.enums.UserState.REGISTERED;

public class ResponseHandler {
    private final SilentSender sender;
    private final Map<Long, UserState> chatStates;
    private final ApplicationEventPublisher publisher;

    public ResponseHandler(SilentSender sender, ApplicationEventPublisher publisher) {
        this.publisher=publisher;
        this.sender = sender;
        chatStates = new HashMap<>();
    }

    public void replyToStart(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(START_TEXT);
        chatStates.put(chatId, AWAITING_EMAIL);
        sender.execute(message);

    }

    public void replyToButtons(long chatId, Message message) {
        if (message.getText().equalsIgnoreCase("/stop")) {
            stopChat(chatId);
        } else {
            switch (chatStates.get(chatId)) {
                case AWAITING_EMAIL -> replyToMail(chatId, message);
                default -> unexpectedMessage(chatId);
            }
        }
    }

    private void stopChat(long chatId) {
        publisher.publishEvent(new TelegramDeleteChatEvent(new TelegramCommand(null, chatId)));
        chatStates.remove(chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(CHAT_UNREGISTRED);
        sender.execute(sendMessage);
    }

    private void unexpectedMessage(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(UNKNOWN_COMMAND);
        sender.execute(sendMessage);
    }

    private void replyToMail(long chatId, Message message) {
        String email = message.getText();
        publisher.publishEvent(new TelegramAddChatEvent(new TelegramCommand(email, chatId)));
    }

    public boolean customNotification(Long chatId, String customMessage) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(customMessage);
        Optional<Message> execute = sender.execute(message);
        return execute.isPresent();
    }

    public boolean userIsActive(Long chatId) {
        try {
            return chatStates.containsKey(chatId);
        } catch (DBException e) {
            return false;
        }
    }

    public void registerChat(long chatId) {
        chatStates.replace(chatId, REGISTERED);
    }
}
