package ru.puchinets.productservice;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    //Logging message
    public static final String LOG_BEGIN = "Beginning method Class: {}, Method: {}, args: {}";
    public static final String LOG_END = "Ending method Class: {}, Method: {}, result: {}";
    public static final String LOG_EXCEPTION = "Exception in method Class: {}, Method: {}, result: {}";
    //Statuses
    public static final String STATUS_SUCCESS="SUCCESS";
    public static final String STATUS_UNSUCCESS="UNSUCCESS";
    //Messages
    public static final String PRODUCT_NOT_FOUND="Product was not found by ID";
    public static final String PRODUCT_HAS_NOT_IN_STOCK="In stock has not quantity product to add in order";
    public static final String INCORRECT_COMMAND = "Received incorrect command";
}
