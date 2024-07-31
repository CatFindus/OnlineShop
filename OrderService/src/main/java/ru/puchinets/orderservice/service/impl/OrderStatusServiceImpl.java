package ru.puchinets.orderservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.puchinets.orderservice.model.entity.OrderStatus;
import ru.puchinets.orderservice.repository.OrderStatusRepository;
import ru.puchinets.orderservice.service.OrderStatusService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderStatusServiceImpl implements OrderStatusService {

    private final OrderStatusRepository repository;

    @Override
    public OrderStatus getFirstOrderStatus() {
        return repository.findByPreviousStatusIsNull();
    }

    @Override
    public List<OrderStatus> getOrderStatuses() {
        return repository.findAll();
    }

    @Override
    public OrderStatus getNextOrderStatus(Short orderStatusId) {
        return repository.findById(orderStatusId)
                .orElseThrow(() -> new IllegalArgumentException("Status with id " + orderStatusId + " not found"))
                .getNextStatus();
    }

    @Override
    public OrderStatus getPrevousOrderStatus(Short orderStatusId) {
        return repository.findById(orderStatusId)
                .orElseThrow(() -> new IllegalArgumentException("Status with id " + orderStatusId + " not found"))
                .getPreviousStatus();
    }


}
