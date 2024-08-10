package ru.puchinets.notificationservice;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    //Telegram
    public static final String CHAT_STATES = "chatStates";
    public static final String START_DESCRIPTION = "Please push start to begin work with bot";
    public static final String START_TEXT = "Welcome to Online Shop Bot!\nTo register an alert, enter the email address provided during registration.";
    public static final String TELEGRAM_ORDER_CREATED_MESSAGE = "Congratulations! Dear user, you have just created an order №%d.";
    public static final String USER_NOT_FOUND = "User with email: %s not found. Please retry again with registered email address";
    public static final String CHAT_REGISTERED = "Chat registered. Wait new notifications";
    public static final String CHAT_UNREGISTRED = "You have unsubscribed from the mailing list. Now you will not receive notifications in the telegram. To renew your subscription, send '/start'.";
    public static final String UNKNOWN_COMMAND = "This command not supported";

    //logs
    public static final String KAFKA_RECEIVE_MESSAGE = "Received message: {}";
    public static final String KAFKA_DESERIALIZATION_EX = "Error to deserialize message from kafka: {}";
    public static final String MAIL_SEND_ERROR = "Can't send mail to {} with message: {}";
    public static final String SMS_SEND_ERROR = "Can't send sms with response message: {}";
    public static final String SERIALIZATION_SUBMIT_ERROR = "Can't serialize message to JSON String with UserData: {}, message: {}";

    //Exceptions
    public static final String USER_UPDATE_COMMAND_TYPE_EX = "Incorrect userUpdateCommandType paramenter";

    //Mail
    public static final String DEFAULT_SUBJECT = "Online Shop Notification";
    public static final String DEFAULT_MESSAGE = "Dear client!\n%s\nSincerely, the Online Shop team";

    //SMS
    public static final String MTS_ERROR_FIELD = "status";
}

