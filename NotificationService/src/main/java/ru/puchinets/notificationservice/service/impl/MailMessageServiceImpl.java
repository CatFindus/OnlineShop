package ru.puchinets.notificationservice.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.puchinets.notificationservice.model.entity.UserData;
import ru.puchinets.notificationservice.service.MessageService;
import ru.puchinets.notificationservice.service.NotificationService;

import static ru.puchinets.notificationservice.Constants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailMessageServiceImpl implements MessageService {

    private final JavaMailSender javaMailSender;
    private final NotificationService service;

    @Override
    public boolean sendMessage(String message, UserData userData) {
        try {
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage, false, "UTF-8");
            mimeMessageHelper.setTo(userData.getData());
            mimeMessageHelper.setSubject(DEFAULT_SUBJECT);
            mimeMessageHelper.setFrom("${mail.username}");
            mimeMessageHelper.setText(String.format(DEFAULT_MESSAGE, message));
            javaMailSender.send(mailMessage);
            return true;
        } catch (MailException | MessagingException e) {
            System.out.println(e.getMessage());
            log.warn(MAIL_SEND_ERROR, userData.getData(), message);
            return false;
        }
    }
}
