package ru.puchinets.productservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.puchinets.productservice.model.dto.request.ChangeProductDto;
import ru.puchinets.productservice.model.dto.request.ProductRequest;
import ru.puchinets.productservice.model.dto.response.ProductResponse;
import ru.puchinets.productservice.model.dto.response.ProductStatusDto;

import java.util.Optional;

public interface ProductService {
    Optional<ProductResponse> getById(Long id);

    Page<ProductResponse> getAll(Pageable pageable);

    ProductResponse create(ProductRequest request);

    Optional<ProductResponse> update(Long id, ProductRequest request);

    boolean delete(Long id);

    ProductStatusDto changeQuantityProductById(Long id, ChangeProductDto command);
}
