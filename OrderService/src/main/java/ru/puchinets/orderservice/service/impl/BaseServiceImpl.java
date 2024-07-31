package ru.puchinets.orderservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import ru.puchinets.orderservice.mapper.BaseMapper;
import ru.puchinets.orderservice.repository.BaseRepository;
import ru.puchinets.orderservice.service.BaseService;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public abstract class BaseServiceImpl<RQ, RS, T, ID> implements BaseService<RQ, RS, T, ID> {
    private final BaseRepository<T, ID> repository;
    private final BaseMapper<RQ, RS, T> mapper;

    public Optional<RS> getById(ID id) {
        return repository.findById(id)
                .map(mapper::modelToDto);
    }

    public Page<RS> getAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::modelToDto);
    }

    @Transactional
    public RS create(RQ request) {
        T entity = mapper.dtoToModel(request);
        repository.saveAndFlush(entity);
        return mapper.modelToDto(entity);
    }

    @Transactional
    public Optional<RS> update(ID id, RQ request) {
        return repository.findById(id)
                .map(entity -> mapper.update(entity, request))
                .map(mapper::modelToDto);
    }

    @Transactional
    public boolean delete(ID id) {
        Optional<T> mayBeEntity = repository.findById(id);
        mayBeEntity.ifPresent(repository::delete);
        return mayBeEntity.isPresent();
    }
}
