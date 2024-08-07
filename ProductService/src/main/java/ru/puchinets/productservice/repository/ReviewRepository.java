package ru.puchinets.productservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.puchinets.productservice.model.entity.Review;

public interface ReviewRepository extends BaseRepository<Review, Long> {

    Page<Review> findAllByProductId(Long productId, Pageable pageable);
    Page<Review> findAllByUserId(Long userId, Pageable pageable);
}
