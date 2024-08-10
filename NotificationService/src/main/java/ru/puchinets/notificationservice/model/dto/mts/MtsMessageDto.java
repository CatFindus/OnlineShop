package ru.puchinets.notificationservice.model.dto.mts;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MtsMessageDto {
    private final List<Submit> submits;
    private final String naming;
}
