package ru.puchinets.productservice.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.puchinets.productservice.mapper.ProductMapper;
import ru.puchinets.productservice.model.dto.request.ChangeProductDto;
import ru.puchinets.productservice.model.dto.request.ProductRequest;
import ru.puchinets.productservice.model.dto.response.ProductResponse;
import ru.puchinets.productservice.model.dto.response.ProductStatusDto;
import ru.puchinets.productservice.model.entity.Product;
import ru.puchinets.productservice.repository.ProductRepository;
import ru.puchinets.productservice.service.ProductService;

import java.util.Optional;

import static ru.puchinets.productservice.Constants.*;


@Service
public class ProductServiceImpl extends BaseService<ProductRequest, ProductResponse, Product,Long> implements ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;


    public ProductServiceImpl(ProductRepository repository, ProductMapper mapper) {
        super(repository,mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public ProductStatusDto changeQuantityProductById(Long id, ChangeProductDto command) {
        Optional<Product> mayBeProduct = repository.findById(id);
        if (mayBeProduct.isEmpty()) {
            return ProductStatusDto.builder()
                    .status(STATUS_UNSUCCESS)
                    .message(PRODUCT_NOT_FOUND)
                    .operationID(command.operationID())
                    .build();
        }
        switch (command.command()) {
            case RESERVE -> {
                return reserveProduct(mayBeProduct.get(), command);
            }
            case ARRIVAL -> {
                return arrivalProduct(mayBeProduct.get(), command);
            }
            case SHIPMENT -> {
                return toShipmentProduct(mayBeProduct.get(), command);
            }
            case WRITE_OFF -> {
                return writeOfProduct(mayBeProduct.get(), command);
            }
            case UNRESERVE -> {
                return unreserveProduct(mayBeProduct.get(), command);
            }
            case UNSHIPMENT -> {
                return unshipProduct(mayBeProduct.get(), command);
            }
            default -> {
                return ProductStatusDto
                        .builder()
                        .productID(id)
                        .status(STATUS_UNSUCCESS)
                        .message(INCORRECT_COMMAND)
                        .operationID(command.operationID())
                        .build();
            }
        }
    }

    private ProductStatusDto unshipProduct(Product product, ChangeProductDto command) {
        if (product.getQuantityToShip()<command.quantity()) {
            return ProductStatusDto
                    .builder()
                    .productID(product.getId())
                    .status(STATUS_UNSUCCESS)
                    .message(PRODUCT_HAS_NOT_IN_STOCK)
                    .operationID(command.operationID())
                    .build();
        } else {
            product.setQuantityInStock(product.getQuantityInReserve()+command.quantity());
            product.setQuantityInReserve(product.getQuantityToShip()-command.quantity());
            repository.saveAndFlush(product);
            return ProductStatusDto
                    .builder()
                    .productID(product.getId())
                    .message(null)
                    .status(STATUS_SUCCESS)
                    .operationID(command.operationID())
                    .build();
        }
    }

    private ProductStatusDto unreserveProduct(Product product, ChangeProductDto command) {
        if (product.getQuantityInReserve()<command.quantity()) {
            return ProductStatusDto
                    .builder()
                    .productID(product.getId())
                    .status(STATUS_UNSUCCESS)
                    .message(PRODUCT_HAS_NOT_IN_STOCK)
                    .operationID(command.operationID())
                    .build();
        } else {
            product.setQuantityInStock(product.getQuantityInStock()+command.quantity());
            product.setQuantityInReserve(product.getQuantityInReserve()-command.quantity());
            repository.saveAndFlush(product);
            return ProductStatusDto
                    .builder()
                    .productID(product.getId())
                    .message(null)
                    .status(STATUS_SUCCESS)
                    .operationID(command.operationID())
                    .build();
        }
    }

    private ProductStatusDto writeOfProduct(Product product, ChangeProductDto command) {
        if (product.getQuantityToShip()<command.quantity()) {
            return ProductStatusDto
                    .builder()
                    .productID(product.getId())
                    .status(STATUS_UNSUCCESS)
                    .message(PRODUCT_HAS_NOT_IN_STOCK)
                    .operationID(command.operationID())
                    .build();
        } else {
            product.setQuantityInStock(product.getQuantityToShip()-command.quantity());
            repository.saveAndFlush(product);
            return ProductStatusDto
                    .builder()
                    .productID(product.getId())
                    .message(null)
                    .status(STATUS_SUCCESS)
                    .operationID(command.operationID())
                    .build();
        }
    }

    private ProductStatusDto toShipmentProduct(Product product, ChangeProductDto command) {
        if (product.getQuantityInReserve()<command.quantity()) {
            return ProductStatusDto
                    .builder()
                    .productID(product.getId())
                    .status(STATUS_UNSUCCESS)
                    .message(PRODUCT_HAS_NOT_IN_STOCK)
                    .operationID(command.operationID())
                    .build();
        } else {
            product.setQuantityInStock(product.getQuantityInReserve()-command.quantity());
            product.setQuantityInReserve(product.getQuantityToShip()+command.quantity());
            repository.saveAndFlush(product);
            return ProductStatusDto
                    .builder()
                    .productID(product.getId())
                    .message(null)
                    .status(STATUS_SUCCESS)
                    .operationID(command.operationID())
                    .build();
        }
    }

    private ProductStatusDto arrivalProduct(Product product, ChangeProductDto command) {
        product.setQuantityInStock(product.getQuantityInStock()+command.quantity());
        repository.saveAndFlush(product);
        return ProductStatusDto
                .builder()
                .productID(product.getId())
                .message(null)
                .status(STATUS_SUCCESS)
                .operationID(command.operationID())
                .build();
    }

    private ProductStatusDto reserveProduct(Product product, ChangeProductDto command) {
        if (product.getQuantityInStock()<command.quantity()) {
            return ProductStatusDto
                    .builder()
                    .productID(product.getId())
                    .status(STATUS_UNSUCCESS)
                    .message(PRODUCT_HAS_NOT_IN_STOCK)
                    .operationID(command.operationID())
                    .build();
        } else {
            product.setQuantityInStock(product.getQuantityInStock()-command.quantity());
            product.setQuantityInReserve(product.getQuantityInReserve()+command.quantity());
            repository.saveAndFlush(product);
            return ProductStatusDto
                    .builder()
                    .productID(product.getId())
                    .message(null)
                    .status(STATUS_SUCCESS)
                    .operationID(command.operationID())
                    .build();
        }
    }
}
