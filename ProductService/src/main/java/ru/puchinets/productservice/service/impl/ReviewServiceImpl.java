package ru.puchinets.productservice.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.puchinets.productservice.mapper.ReviewMapper;
import ru.puchinets.productservice.model.dto.request.ReviewRequest;
import ru.puchinets.productservice.model.dto.response.ReviewResponse;
import ru.puchinets.productservice.model.entity.Review;
import ru.puchinets.productservice.repository.ReviewRepository;
import ru.puchinets.productservice.service.ReviewService;

@Service
@Transactional(readOnly = true)
public class ReviewServiceImpl extends BaseService<ReviewRequest, ReviewResponse, Review, Long> implements ReviewService {
    private final ReviewRepository repository;
    private final ReviewMapper mapper;

    public ReviewServiceImpl(ReviewRepository repository, ReviewMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<ReviewResponse> findAllByProductId(Long productId, Pageable pageable) {
        return repository.findAllByProductId(productId, pageable).map(mapper::modelToDto);
    }

    @Override
    public Page<ReviewResponse> findAllForUser(Long userId, Pageable pageable) {
        return repository.findAllByUserId(userId, pageable).map(mapper::modelToDto);
    }
}
