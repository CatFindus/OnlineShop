package ru.puchinets.productservice.model.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
    private CategoryResponse parent;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
