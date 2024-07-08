package ru.puchinets.userservice;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    //Validation Messages
    public final static String USERNAME_LENGTH_MSG = "Username need to between 8 and 128 characters";

    //Logging message
    public static final String LOG_BEGIN = "Beginning method Class: {}, Method: {}, args: {}";
    public static final String LOG_END = "Ending method Class: {}, Method: {}, result: {}";
    public static final String LOG_EXCEPTION = "Exception in method Class: {}, Method: {}, result: {}";

}
