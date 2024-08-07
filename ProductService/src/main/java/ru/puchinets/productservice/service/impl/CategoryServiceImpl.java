package ru.puchinets.productservice.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.puchinets.productservice.mapper.CategoryMapper;
import ru.puchinets.productservice.model.dto.request.CategoryRequest;
import ru.puchinets.productservice.model.dto.response.CategoryResponse;
import ru.puchinets.productservice.model.dto.response.CategoryShortResponse;
import ru.puchinets.productservice.model.entity.Category;
import ru.puchinets.productservice.repository.CategoryRepository;
import ru.puchinets.productservice.service.CategoryService;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl extends BaseService<CategoryRequest, CategoryResponse, Category, Long> implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        super(categoryRepository, categoryMapper);
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public Page<CategoryShortResponse> getAllShort(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(categoryMapper::modelToShortDto);
    }
}
