package ru.puchinets.ratesservice.utils.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import ru.puchinets.ratesservice.Constants;
import ru.puchinets.ratesservice.mapper.DtoMapper;
import ru.puchinets.ratesservice.model.dto.cb.CbRateDto;
import ru.puchinets.ratesservice.model.entity.Currency;
import ru.puchinets.ratesservice.model.entity.Rate;
import ru.puchinets.ratesservice.utils.CurrencyUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CurrencyUtilCbImpl implements CurrencyUtil {

    private final DtoMapper cbMapper;

    private final XmlMapper mapper;

    @Override
    public Map<Currency,Rate> getRatesForDate(LocalDate date) throws JsonProcessingException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.CB_DATE_PATTERN);
        String dateForRequest = date.format(formatter);
        RestClient restClient = RestClient.create();
        String rates = restClient.get()
                .uri(Constants.CB_URI+dateForRequest)
                .retrieve()
                .body(String.class);
        CbRateDto cbRateDto = mapper.readValue(rates, CbRateDto.class);
        return cbMapper.fromDto(cbRateDto);
    }
}
