package ru.puchinets.orderservice.service.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.puchinets.orderservice.events.OrderCreatedEvent;
import ru.puchinets.orderservice.mapper.OrderMapper;
import ru.puchinets.orderservice.model.dto.request.OrderRequest;
import ru.puchinets.orderservice.model.dto.response.OrderResponse;
import ru.puchinets.orderservice.model.entity.Order;
import ru.puchinets.orderservice.model.entity.OrderStatus;
import ru.puchinets.orderservice.repository.OrderRepository;
import ru.puchinets.orderservice.service.OrderService;
import ru.puchinets.orderservice.service.OrderStatusService;

import java.math.BigDecimal;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl extends BaseServiceImpl<OrderRequest, OrderResponse, Order, Long> implements OrderService {
    private final OrderMapper mapper;
    private final OrderRepository repository;
    private final OrderStatusService orderStatusService;
    private final ApplicationEventPublisher publisher;

    public OrderServiceImpl(OrderRepository repository, OrderMapper mapper, OrderStatusService orderStatusService, ApplicationEventPublisher publisher) {
        super(repository, mapper);
        this.repository=repository;
        this.mapper=mapper;
        this.orderStatusService=orderStatusService;
        this.publisher = publisher;
    }

    @Transactional
    @Override
    public OrderResponse create(OrderRequest request) {
        OrderStatus status = orderStatusService.getFirstOrderStatus();
        Order order = mapper.dtoToModel(request);
        order.setStatus(status);
        order.setTotalPrice(BigDecimal.ZERO);
        order = repository.save(order);
        publisher.publishEvent(new OrderCreatedEvent(order));
        return mapper.modelToDto(order);
    }

    @Override
    public Page<OrderResponse> getAllForUser(Long userId, Pageable pageable) {
        return repository.findAllByUserId(userId, pageable)
                .map(mapper::modelToDto);
    }
}
