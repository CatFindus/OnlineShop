package ru.puchinets.productservice.mapper;

import org.mapstruct.Mapper;
import ru.puchinets.productservice.model.dto.request.ProductRequest;
import ru.puchinets.productservice.model.dto.response.ProductResponse;
import ru.puchinets.productservice.model.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<ProductRequest, ProductResponse, Product> {

    ProductResponse modelToDto(Product entity);
    Product dtoToModel(ProductRequest request);
    default Product update(Product product, ProductRequest request) {
        if (request==null) return product;
        if (request.getCategoryId()!=null)
            product.setCategoryId(request.getCategoryId());
        if (request.getDescription()!=null)
            product.setDescription(request.getDescription());
        if (request.getName()!=null && !request.getName().isBlank())
            product.setName(request.getName());
        if (request.getPrice()!=null)
            product.setPrice(request.getPrice());
        if (request.getQuantityInStock()!=null)
            product.setQuantityInStock(request.getQuantityInStock());
        return product;
    }
}
