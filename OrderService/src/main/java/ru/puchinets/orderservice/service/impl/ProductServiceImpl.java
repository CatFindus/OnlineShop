package ru.puchinets.orderservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.puchinets.orderservice.config.ProductServiceProperties;
import ru.puchinets.orderservice.enums.ChangeProductCommand;
import ru.puchinets.orderservice.model.dto.ChangeProductDto;
import ru.puchinets.orderservice.model.dto.ProductDto;
import ru.puchinets.orderservice.model.dto.ProductStatusDto;
import ru.puchinets.orderservice.service.ProductService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductServiceProperties properties;

    @Override
    public ProductDto findProductById(Long productId) {
        RestClient client = RestClient.create();
        return client.get()
                .uri(properties.getUrl() + properties.getPath() + "/" + productId)
                .retrieve()
                .body(ProductDto.class);
    }

    @Override
    public ProductStatusDto changeProductById(Long productId, Integer quantity, UUID operationID, ChangeProductCommand command) {
        ChangeProductDto dto = ChangeProductDto
                .builder()
                .productID(productId)
                .command(command)
                .quantity(quantity)
                .operationID(operationID)
                .build();
        return RestClient
                .create()
                .patch()
                .uri(properties.getUrl() + properties.getPath() + "/" + productId)
                .body(dto)
                .retrieve()
                .body(ProductStatusDto.class);
    }
}
