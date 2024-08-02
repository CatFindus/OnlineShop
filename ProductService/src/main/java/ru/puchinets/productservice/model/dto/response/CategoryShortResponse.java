package ru.puchinets.productservice.model.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryShortResponse {
    private Long id;
    private String name;
    private String description;
}
