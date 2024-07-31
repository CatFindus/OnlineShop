package ru.puchinets.orderservice.service.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.puchinets.orderservice.events.OrderItemAddEvent;
import ru.puchinets.orderservice.exceptions.QuantityException;
import ru.puchinets.orderservice.mapper.OrderItemMapper;
import ru.puchinets.orderservice.model.dto.ProductStatusDto;
import ru.puchinets.orderservice.model.dto.request.OrderItemRequest;
import ru.puchinets.orderservice.model.dto.response.OrderItemResponse;
import ru.puchinets.orderservice.model.entity.OrderItem;
import ru.puchinets.orderservice.repository.OrderItemRepository;
import ru.puchinets.orderservice.service.OrderItemService;
import ru.puchinets.orderservice.service.ProductService;

import java.util.Optional;
import java.util.UUID;

import static ru.puchinets.orderservice.Constants.STATUS_UNSUCCESS;
import static ru.puchinets.orderservice.enums.ChangeProductCommand.RESERVE;
import static ru.puchinets.orderservice.enums.ChangeProductCommand.UNRESERVE;

@Service
@Transactional(readOnly = true)
public class OrderItemServiceImpl extends BaseServiceImpl<OrderItemRequest, OrderItemResponse, OrderItem, Long> implements OrderItemService {

    private final OrderItemRepository repository;
    private final OrderItemMapper mapper;
    private final ProductService productService;
    private final ApplicationEventPublisher publisher;

    public OrderItemServiceImpl(OrderItemRepository repository,
                                OrderItemMapper mapper,
                                ProductService productService,
                                ApplicationEventPublisher publisher) {
        super(repository, mapper);
        this.repository=repository;
        this.mapper=mapper;
        this.productService=productService;
        this.publisher=publisher;
    }

    @Override
    public Page<OrderItemResponse> findAllByOrderId(Long orderId, Pageable pageable) {
        return repository.findAllByOrderId(orderId, pageable)
                .map(mapper::modelToDto);
    }

    @Override
    public OrderItemResponse create(OrderItemRequest request) {
        UUID uuid = UUID.randomUUID();
        ProductStatusDto status = productService.changeProductById(request.getProductId(),
                request.getQuantity(),
                uuid,
                RESERVE);
        if (status.status().equals(STATUS_UNSUCCESS)) throw new QuantityException(status.message());
        if (!status.operationID().equals(uuid))
            throw new RuntimeException(String.format("Unknown error: the operation id is violated. Sent: %S, Received: %S", uuid, status.operationID()));
        OrderItemResponse response = super.create(request);
        publisher.publishEvent(new OrderItemAddEvent(response));
        return response;
    }
    @Transactional
    @Override
    public boolean delete(Long id) {
        UUID uuid = UUID.randomUUID();
        Optional<OrderItem> mayBeItem = repository.findById(id);
        if (mayBeItem.isEmpty()) return false;
        ProductStatusDto status = productService.changeProductById(mayBeItem.get().getOrderId(), mayBeItem.get().getQuantity(), uuid, UNRESERVE);
        boolean isDeleted = super.delete(id);
        if (status.status().equals(STATUS_UNSUCCESS)) throw new QuantityException(status.message());
        if (!status.operationID().equals(uuid))
            throw new RuntimeException(String.format("Unknown error: the operation id is violated. Sent: %S, Received: %S", uuid, status.operationID()));
        return isDeleted;
    }

    @Transactional
    @Override
    public Optional<OrderItemResponse> update(Long id, OrderItemRequest request) {
        Optional<OrderItem> old = repository.findById(id);
        if (old.isEmpty()) return Optional.empty();
        UUID uuidReserve = UUID.randomUUID();
        UUID uuidUnreserve = UUID.randomUUID();
        ProductStatusDto reserveStatus = productService.changeProductById(request.getProductId(), request.getQuantity(), uuidReserve, RESERVE);
        productService.changeProductById(old.get().getProductId(), old.get().getQuantity(), uuidUnreserve, UNRESERVE);
        if (reserveStatus.status().equals(STATUS_UNSUCCESS) ||
            !reserveStatus.operationID().equals(uuidReserve)) {
            return Optional.empty();
        }
        return super.update(id, request);
    }
}
