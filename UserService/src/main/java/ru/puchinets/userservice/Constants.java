package ru.puchinets.userservice;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    //Roles
    public static final String DEFAULT_ROLE = "user";
    //Validation Messages
    public final static String PASSWORD_LENGTH_MSG = "Password need to between 8 and 128 characters";

    //Logging message
    public static final String LOG_BEGIN = "Beginning method Class: {}, Method: {}, args: {}";
    public static final String LOG_END = "Ending method Class: {}, Method: {}, result: {}";
    public static final String LOG_EXCEPTION = "Exception in method Class: {}, Method: {}, result: {}";
    //Swagger examples
    public static final String PAGINATION_EXAMPLE="{\n\"page\": 0,\n\"size\": 2,\n\"sort\": \"id,desc\"\n}";
}
