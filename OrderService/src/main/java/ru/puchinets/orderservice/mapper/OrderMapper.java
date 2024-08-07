package ru.puchinets.orderservice.mapper;

import org.mapstruct.Mapper;
import ru.puchinets.orderservice.model.dto.request.OrderRequest;
import ru.puchinets.orderservice.model.dto.response.OrderResponse;
import ru.puchinets.orderservice.model.entity.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper extends BaseMapper<OrderRequest, OrderResponse, Order> {
    @Override
    OrderResponse modelToDto(Order entity);

    @Override
    Order dtoToModel(OrderRequest request);

    @Override
    default Order update(Order entity, OrderRequest request) {
        if (request==null) return entity;
        if (request.getStatus()!=null) entity.setStatus(request.getStatus());
        if (request.getTotalPrice()!=null) entity.setTotalPrice(request.getTotalPrice());
        return entity;
    }
}
