package ru.puchinets.productservice.mapper;

import org.mapstruct.Mapper;
import ru.puchinets.productservice.model.dto.request.CategoryRequest;
import ru.puchinets.productservice.model.dto.response.CategoryResponse;
import ru.puchinets.productservice.model.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<CategoryRequest, CategoryResponse, Category> {
    @Override
    CategoryResponse modelToDto(Category entity);
    @Override
    Category dtoToModel(CategoryRequest request);
    @Override
    default Category update(Category entity, CategoryRequest request) {
        if (request==null) return entity;
        if (request.getName()!=null && !request.getName().isBlank())
            entity.setName(request.getName());
        if (request.getDescription()!=null)
            entity.setDescription(request.getDescription());
        return entity;
    }
}
