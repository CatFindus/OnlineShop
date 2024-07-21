package ru.puchinets.productservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.puchinets.productservice.model.dto.request.ReviewRequest;
import ru.puchinets.productservice.model.dto.response.ReviewResponse;

import java.util.Optional;

public interface ReviewService {

    Optional<ReviewResponse> getById(Long id);

    Page<ReviewResponse> getAll(Pageable pageable);

    ReviewResponse create(ReviewRequest request);

    Optional<ReviewResponse> update(Long id, ReviewRequest request);

    boolean delete(Long id);

    Page<ReviewResponse> findAllByProductId(Long productId, Pageable pageable);

    Page<ReviewResponse> findAllForUser(Long userId, Pageable pageable);
}
