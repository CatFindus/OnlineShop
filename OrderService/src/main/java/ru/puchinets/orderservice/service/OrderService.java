package ru.puchinets.orderservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.puchinets.orderservice.model.dto.request.OrderRequest;
import ru.puchinets.orderservice.model.dto.response.OrderResponse;
import ru.puchinets.orderservice.model.entity.Order;

public interface OrderService extends BaseService<OrderRequest, OrderResponse, Order, Long> {

    Page<OrderResponse> getAllForUser(Long userId, Pageable pageable);
}
