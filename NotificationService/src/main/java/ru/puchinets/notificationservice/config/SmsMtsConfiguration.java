package ru.puchinets.notificationservice.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import ru.puchinets.notificationservice.Constants;

@Slf4j
@Configuration
public class SmsMtsConfiguration {
    @Value("${sms.token}")
    private String token;
    @Value("${sms.url}")
    private String mtsApiUrl;
    @Value("${sms.naming}")
    @Getter
    private String naming;
    @Value("${sms.active}")
    @Getter
    private boolean active;

    public boolean sendMessage(String messageDto) {
        RestClient restClient = RestClient.create();
        String response = restClient
                .post()
                .uri(mtsApiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization: Bearer token", token)
                .body(messageDto)
                .retrieve()
                .body(String.class);
        if (response == null || response.contains("status")) {
            log.warn(Constants.SMS_SEND_ERROR, response);
            return false;
        } else return true;
    }
}

