package ru.puchinets.ratesservice;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    //API
    public static final String CB_URI = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=";
    public static final String CB_DATE_PATTERN = "dd/MM/yyyy";
    //Logger
    public static final String ERROR_RECEIVE_CURRENCIES="Error when receiving currencies from API for date: {}, with message: {}";
    public static final String WARN_NO_CURRENCIES_FOUND = "No currencies found in database";
    public static final String WARN_NO_RATES_FOUND = "No rates found in database for date: {}";
    public static final String LOG_BEGIN = "Beginning method Class: {}, Method: {}, args: {}";
    public static final String LOG_END = "Ending method Class: {}, Method: {}, result: {}";
    public static final String LOG_EXCEPTION = "Exception in method Class: {}, Method: {}, result: {}";
    //Exceptions
    public static final String INCORRECT_CURRENCY_CODE = "Incorrect input currency code(s)";
}
