package ru.puchinets.ratesservice.mapper.impl;

import org.springframework.stereotype.Component;
import ru.puchinets.ratesservice.mapper.DtoMapper;
import ru.puchinets.ratesservice.model.dto.RateDto;
import ru.puchinets.ratesservice.model.dto.cb.CbRateDto;
import ru.puchinets.ratesservice.model.dto.cb.CbValuteDto;
import ru.puchinets.ratesservice.model.entity.Currency;
import ru.puchinets.ratesservice.model.entity.Rate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Component
public class CbMapper implements DtoMapper {

    @Override
    public Map<Currency, Rate> fromDto(RateDto dto) {
        CbRateDto cbDto = (CbRateDto) dto;
        Map<Currency, Rate> result = new HashMap<>();
        for (CbValuteDto cbValuteDto : cbDto.getCbValuteDtos()) {
            BigDecimal amount = cbValuteDto.getAmount().divide(BigDecimal.valueOf(cbValuteDto.getNominal()), RoundingMode.DOWN);
            Currency currency = new Currency(cbValuteDto.getId(), cbValuteDto.getCode(), cbValuteDto.getName());
            Rate rate = new Rate(cbValuteDto.getId(), cbValuteDto.getCode(), amount, cbDto.getDate());
            result.put(currency, rate);
        }
        return result;
    }
}
