package ru.puchinets.orderservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BaseService<RQ, RS, T, ID> {
    Optional<RS> getById(ID id);
    Page<RS> getAll(Pageable pageable);
    RS create(RQ request);
    Optional<RS> update(ID id, RQ request);
    boolean delete(ID id);
}
