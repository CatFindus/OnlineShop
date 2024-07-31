package ru.puchinets.ratesservice.service;

import ru.puchinets.ratesservice.model.entity.Currency;
import ru.puchinets.ratesservice.model.entity.Rate;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RateService {
    List<Currency> getCurrencies();
    List<Rate> getRates(LocalDate date, String codeFrom, String codeTo);
    Optional<Rate> getRate(LocalDate date, String code);
    Map<Currency, Rate> updateDbForDate(LocalDate date);
}
