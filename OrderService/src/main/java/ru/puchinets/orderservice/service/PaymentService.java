package ru.puchinets.orderservice.service;

import ru.puchinets.orderservice.model.dto.request.PaymentRequest;
import ru.puchinets.orderservice.model.dto.response.PaymentResponse;
import ru.puchinets.orderservice.model.entity.Payment;

public interface PaymentService extends BaseService<PaymentRequest, PaymentResponse, Payment, Long>{
}
