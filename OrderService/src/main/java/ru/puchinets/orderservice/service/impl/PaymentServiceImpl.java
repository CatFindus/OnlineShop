package ru.puchinets.orderservice.service.impl;

import org.springframework.stereotype.Service;
import ru.puchinets.orderservice.mapper.PaymentMapper;
import ru.puchinets.orderservice.model.dto.request.PaymentRequest;
import ru.puchinets.orderservice.model.dto.response.PaymentResponse;
import ru.puchinets.orderservice.model.entity.Payment;
import ru.puchinets.orderservice.repository.PaymentRepository;
import ru.puchinets.orderservice.service.PaymentService;

@Service
public class PaymentServiceImpl extends BaseServiceImpl<PaymentRequest, PaymentResponse, Payment, Long> implements PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper mapper;

    public PaymentServiceImpl(PaymentRepository repository, PaymentMapper mapper) {
        super(repository, mapper);
        this.repository=repository;
        this.mapper=mapper;
    }
}
