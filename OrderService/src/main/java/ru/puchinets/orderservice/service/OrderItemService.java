package ru.puchinets.orderservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import ru.puchinets.orderservice.model.dto.request.OrderItemRequest;
import ru.puchinets.orderservice.model.dto.response.OrderItemResponse;
import ru.puchinets.orderservice.model.dto.response.OrderResponse;
import ru.puchinets.orderservice.model.entity.OrderItem;

public interface OrderItemService extends BaseService<OrderItemRequest, OrderItemResponse, OrderItem, Long> {

    Page<OrderItemResponse> findAllByOrderId(Long orderId, Pageable pageable);
}
