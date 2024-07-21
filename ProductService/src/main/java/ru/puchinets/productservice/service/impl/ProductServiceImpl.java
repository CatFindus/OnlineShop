package ru.puchinets.productservice.service.impl;

import org.springframework.stereotype.Service;
import ru.puchinets.productservice.mapper.ProductMapper;
import ru.puchinets.productservice.model.dto.request.ProductRequest;
import ru.puchinets.productservice.model.dto.response.ProductResponse;
import ru.puchinets.productservice.model.entity.Product;
import ru.puchinets.productservice.repository.ProductRepository;
import ru.puchinets.productservice.service.ProductService;


@Service
public class ProductServiceImpl extends BaseService<ProductRequest, ProductResponse, Product,Long> implements ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;


    public ProductServiceImpl(ProductRepository repository, ProductMapper mapper) {
        super(repository,mapper);
        this.repository = repository;
        this.mapper = mapper;
    }
}
