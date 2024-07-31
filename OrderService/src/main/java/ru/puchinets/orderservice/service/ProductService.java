package ru.puchinets.orderservice.service;

import ru.puchinets.orderservice.enums.ChangeProductCommand;
import ru.puchinets.orderservice.model.dto.ProductDto;
import ru.puchinets.orderservice.model.dto.ProductStatusDto;

import java.util.UUID;

public interface ProductService {
    ProductDto findProductById(Long productId);
    ProductStatusDto changeProductById(Long productId, Integer quantity, UUID operationID, ChangeProductCommand command);
}
