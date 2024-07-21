package ru.puchinets.productservice.service.impl;

import org.springframework.stereotype.Service;
import ru.puchinets.productservice.mapper.CategoryMapper;
import ru.puchinets.productservice.model.dto.request.CategoryRequest;
import ru.puchinets.productservice.model.dto.response.CategoryResponse;
import ru.puchinets.productservice.model.entity.Category;
import ru.puchinets.productservice.repository.CategoryRepository;
import ru.puchinets.productservice.service.CategoryService;

@Service
public class CategoryServiceImpl extends BaseService<CategoryRequest, CategoryResponse, Category, Long> implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        super(categoryRepository, categoryMapper);
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }
}
