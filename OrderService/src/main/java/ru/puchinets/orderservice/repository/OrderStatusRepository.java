package ru.puchinets.orderservice.repository;

import ru.puchinets.orderservice.model.entity.OrderStatus;

public interface OrderStatusRepository extends BaseRepository<OrderStatus, Short>{
    OrderStatus findByPreviousStatusIsNull();
}
