package ru.puchinets.orderservice.repository;

import ru.puchinets.orderservice.model.entity.OrderStatus;

import java.util.Optional;

public interface OrderStatusRepository extends BaseRepository<OrderStatus, Short>{
    OrderStatus findByPreviousStatusIdIsNull();
    Optional<OrderStatus> findByPreviousStatusId(Short statusId);
    Optional<OrderStatus> findByNextStatusId(Short statusId);
}
