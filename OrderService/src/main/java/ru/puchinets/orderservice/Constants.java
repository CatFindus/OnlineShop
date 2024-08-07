package ru.puchinets.orderservice;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    //Notification types
    public static final String ORDER_CREATED_NOTIFICATION_TYPE = "ORDER_CREATED";

    //Statuses
    public static final String STATUS_SUCCESS="SUCCESS";
    public static final String STATUS_UNSUCCESS="UNSUCCESS";

    //Kafka
    public static final String ORDER_ITEM_LIFECYCLE_KAFKA_TOPIC = "statistic";
    public static final String ORDER_CREATED_KAFKA_TOPIC = "order-created";

    //Logs
    public static final String KAFKA_SEND_MESSAGE = "Message sent to topic {} : {}";
    public static final String LOG_BEGIN = "Beginning method Class: {}, Method: {}, args: {}";
    public static final String LOG_END = "Ending method Class: {}, Method: {}, result: {}";
    public static final String LOG_EXCEPTION = "Exception in method Class: {}, Method: {}, result: {}";
}
