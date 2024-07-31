package ru.puchinets.ratesservice.mapper;

import ru.puchinets.ratesservice.model.dto.RateDto;
import ru.puchinets.ratesservice.model.entity.Currency;
import ru.puchinets.ratesservice.model.entity.Rate;

import java.util.Map;

public interface DtoMapper {

    Map<Currency, Rate> fromDto(RateDto dto);
}
