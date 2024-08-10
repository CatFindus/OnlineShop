package ru.puchinets.notificationservice.model.dto.mts;


import java.io.Serializable;

public record Submit(String msid, String message) implements Serializable {
}
