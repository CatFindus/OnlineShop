package ru.puchinets.orderservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.puchinets.orderservice.model.entity.OrderItem;

public interface OrderItemRepository extends BaseRepository<OrderItem, Long>{
    Page<OrderItem> findAllByOrderId(Long orderId, Pageable pageable);
}
