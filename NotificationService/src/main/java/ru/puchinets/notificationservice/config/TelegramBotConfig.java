package ru.puchinets.notificationservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.bot.BaseAbilityBot;
import org.telegram.abilitybots.api.objects.*;
import org.telegram.abilitybots.api.util.AbilityUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.puchinets.notificationservice.handler.ResponseHandler;

import java.util.function.BiConsumer;

import static org.telegram.abilitybots.api.util.AbilityUtils.*;
import static ru.puchinets.notificationservice.Constants.START_DESCRIPTION;

@Component
public class TelegramBotConfig extends AbilityBot {

    private final ResponseHandler responseHandler;
    @Autowired
    public TelegramBotConfig(Environment env, ApplicationEventPublisher publisher) {
        super(env.getProperty("BOT_TOKEN"), "cat_findus_bot");
        responseHandler = new ResponseHandler(silent, publisher);
    }

    @Override
    public long creatorId() {
        return 1L;
    }

    public Ability startBot() {
        return Ability
                .builder()
                .name("start")
                .info(START_DESCRIPTION)
                .locality(Locality.USER)
                .privacy(Privacy.PUBLIC)
                .action(ctx -> responseHandler.replyToStart(ctx.chatId()))
                .build();
    }


    public Reply replyButton() {
        BiConsumer<BaseAbilityBot, Update> action = ((baseAbilityBot, update) -> responseHandler.replyToButtons(getChatId(update), update.getMessage()));
        return Reply.of(action, Flag.TEXT, update -> responseHandler.userIsActive(getChatId(update)));
    }

    public boolean sendCustomMessage(Long chatId, String text) {
        return responseHandler.customNotification(chatId, text);
    }

    public void registerChat(long chatId) {
        responseHandler.registerChat(chatId);
    }
}
