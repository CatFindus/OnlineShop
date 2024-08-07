package ru.puchinets.ratesservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.puchinets.ratesservice.model.entity.Currency;
import ru.puchinets.ratesservice.model.entity.Rate;
import ru.puchinets.ratesservice.repository.CurrencyRepository;
import ru.puchinets.ratesservice.repository.RateRepository;
import ru.puchinets.ratesservice.service.RateService;
import ru.puchinets.ratesservice.utils.CurrencyUtil;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static ru.puchinets.ratesservice.Constants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class    RateServiceImpl implements RateService {

    private final CurrencyRepository currencyRepository;
    private final RateRepository rateRepository;
    private final CurrencyUtil currencyUtilCb;

    @Override
    @Transactional
    public List<Currency> getCurrencies() {
        List<Currency> currencies = currencyRepository.findAll();
        if (currencies.isEmpty()) {
            log.warn(WARN_NO_CURRENCIES_FOUND);
            Map<Currency, Rate> map = updateDbForDate(LocalDate.now());
            currencies = map.keySet().stream().toList();
        }
        return currencies;
    }

    @Transactional
    @Override
    public List<Rate> getRates(LocalDate date, String codeFrom, String codeTo) {
        if (date==null || codeFrom.isBlank() || codeTo.isBlank()) throw new IllegalArgumentException(INCORRECT_CURRENCY_CODE);
        List<String> codes = List.of(codeFrom.toUpperCase(), codeTo.toUpperCase());
        List<Rate> rates = rateRepository.findAllByCodeInAndDate(codes, date);
        if (rates.isEmpty()) {
            log.warn(WARN_NO_RATES_FOUND, date);
            rates = updateDbForDate(date).values().stream().filter(rate -> codes.contains(rate.getCode())).toList();
        }
        return rates;
    }

    @Transactional
    @Override
    public Optional<Rate> getRate(LocalDate date, String code) {
        if (date==null || code.isBlank()) throw new IllegalArgumentException(INCORRECT_CURRENCY_CODE);
        Optional<Rate> mayBeRate = rateRepository.findByCodeAndDate(code.toUpperCase(), date);
        if (mayBeRate.isEmpty()) {
            log.warn(WARN_NO_RATES_FOUND, date);
            Map<Currency, Rate> currencyRateMap = updateDbForDate(date);
            mayBeRate = currencyRateMap.values().stream().filter(rate -> rate.getCode().equalsIgnoreCase(code)).findFirst();
        }
        return mayBeRate;
    }

    @Transactional
    public Map<Currency, Rate> updateDbForDate(LocalDate date) {
        Map<Currency, Rate> ratesForDate = new HashMap<>();
        try {
            ratesForDate = currencyUtilCb.getRatesForDate(date);
            currencyRepository.saveAll(ratesForDate.keySet());
            rateRepository.saveAll(ratesForDate.values());

        } catch (JsonProcessingException e) {
            log.error(ERROR_RECEIVE_CURRENCIES, date, e.getMessage());
        }
        return ratesForDate;
    }
}
