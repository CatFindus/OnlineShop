package ru.puchinets.orderservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.puchinets.orderservice.model.entity.Order;


public interface OrderRepository extends BaseRepository<Order, Long>{
    Page<Order> findAllByUserId(Long userId, Pageable pageable);
}
