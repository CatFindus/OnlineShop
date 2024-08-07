package ru.puchinets.ratesservice.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.puchinets.ratesservice.model.entity.Currency;
import ru.puchinets.ratesservice.model.entity.Rate;

import java.time.LocalDate;
import java.util.Map;

public interface CurrencyUtil {
    Map<Currency,Rate> getRatesForDate(LocalDate date) throws JsonProcessingException;
}
