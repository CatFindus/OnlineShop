package ru.puchinets.productservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.puchinets.productservice.model.dto.request.CategoryRequest;
import ru.puchinets.productservice.model.dto.response.CategoryResponse;
import ru.puchinets.productservice.model.dto.response.CategoryShortResponse;

import java.util.Optional;

public interface CategoryService {
    Optional<CategoryResponse> getById(Long id);

    Page<CategoryResponse> getAll(Pageable pageable);

    CategoryResponse create(CategoryRequest request);

    Optional<CategoryResponse> update(Long id, CategoryRequest request);

    boolean delete(Long id);

    Page<CategoryShortResponse> getAllShort(Pageable pageable);
}
