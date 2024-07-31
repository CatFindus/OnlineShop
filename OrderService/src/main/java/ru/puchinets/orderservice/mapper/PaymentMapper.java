package ru.puchinets.orderservice.mapper;

import org.mapstruct.Mapper;
import ru.puchinets.orderservice.model.dto.request.PaymentRequest;
import ru.puchinets.orderservice.model.dto.response.PaymentResponse;
import ru.puchinets.orderservice.model.entity.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper extends BaseMapper<PaymentRequest, PaymentResponse, Payment> {
    @Override
    PaymentResponse modelToDto(Payment entity);

    @Override
    Payment dtoToModel(PaymentRequest request);

    @Override
    default Payment update(Payment entity, PaymentRequest request) {
        if (request == null) return entity;
        if (request.getPaymentStatus() != null && !request.getPaymentStatus().isBlank()) {
            entity.setPaymentStatus(request.getPaymentStatus());
        }
        return entity;
    }
}
