package ru.puchinets.orderservice.service;

import ru.puchinets.orderservice.model.entity.OrderStatus;

import java.util.List;

public interface OrderStatusService {
    OrderStatus getFirstOrderStatus();
    List<OrderStatus> getOrderStatuses();
    OrderStatus getNextOrderStatus(Short orderStatusId);
    OrderStatus getPrevousOrderStatus(Short orderStatusId);
}
