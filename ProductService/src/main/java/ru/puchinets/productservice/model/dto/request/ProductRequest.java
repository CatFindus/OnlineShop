package ru.puchinets.productservice.model.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantityInStock;
    private Long categoryId;
}
