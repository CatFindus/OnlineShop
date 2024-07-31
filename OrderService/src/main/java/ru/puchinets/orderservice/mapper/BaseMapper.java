package ru.puchinets.orderservice.mapper;

public interface BaseMapper<RQ, RS, T> {
    RS modelToDto(T entity);
    T dtoToModel(RQ request);
    T update(T entity, RQ request);
}
