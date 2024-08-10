package ru.puchinets.notificationservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.puchinets.notificationservice.Constants;
import ru.puchinets.notificationservice.config.SmsMtsConfiguration;
import ru.puchinets.notificationservice.model.dto.mts.MtsMessageDto;
import ru.puchinets.notificationservice.model.dto.mts.Submit;
import ru.puchinets.notificationservice.model.entity.UserData;
import ru.puchinets.notificationservice.service.MessageService;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class SmsMessageServiceImpl implements MessageService {
    private final SmsMtsConfiguration configuration;
    private final ObjectMapper mapper;

    @Override
    public boolean sendMessage(String message, UserData userData) {
        if (!configuration.isActive() || message.isBlank() || userData==null) return false;
        try {
            Submit submit = new Submit(userData.getData(), message);
            MtsMessageDto dto = new MtsMessageDto(List.of(submit), configuration.getNaming());
            String dtoMessage = mapper.writeValueAsString(dto);
            return configuration.sendMessage(dtoMessage);
        } catch (JsonProcessingException e) {
            log.warn(Constants.SERIALIZATION_SUBMIT_ERROR, userData, message);
            return false;
        }
    }
}
