package ru.puchinets.productservice.model.dto.request;

import lombok.Data;

@Data
public class CategoryRequest {
    private String name;
    private String description;
}
