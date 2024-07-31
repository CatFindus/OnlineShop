package ru.puchinets.orderservice.mapper;

import org.mapstruct.Mapper;
import ru.puchinets.orderservice.model.dto.request.OrderItemRequest;
import ru.puchinets.orderservice.model.dto.response.OrderItemResponse;
import ru.puchinets.orderservice.model.entity.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper extends BaseMapper<OrderItemRequest, OrderItemResponse, OrderItem> {

    @Override
    OrderItemResponse modelToDto(OrderItem entity);

    @Override
    OrderItem dtoToModel(OrderItemRequest request);

    @Override
    default OrderItem update(OrderItem entity, OrderItemRequest request) {
        if (request==null) return entity;
        if (request.getProductId()!=null &&
            request.getQuantity()!=null &&
            request.getPrice()!=null) {
            entity.setProductId(request.getProductId());
            entity.setQuantity(request.getQuantity());
            entity.setPrice(request.getPrice());
        }
        return entity;
    }
}
