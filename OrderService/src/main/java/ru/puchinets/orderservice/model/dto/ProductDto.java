package ru.puchinets.orderservice.model.dto;


import java.math.BigDecimal;

public record ProductDto(Long id, String name, String description, BigDecimal price, Integer quantityInStock,
                         Long categoryId) {
}
