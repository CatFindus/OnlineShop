package ru.puchinets.productservice.mapper;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import ru.puchinets.productservice.model.dto.request.CategoryRequest;
import ru.puchinets.productservice.model.dto.response.CategoryResponse;
import ru.puchinets.productservice.model.dto.response.CategoryShortResponse;
import ru.puchinets.productservice.model.entity.Category;
import ru.puchinets.productservice.repository.CategoryRepository;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CategoryMapper implements BaseMapper<CategoryRequest, CategoryResponse, Category> {

    @Autowired
    private CategoryRepository repository;

    @Override
    public abstract CategoryResponse modelToDto(Category entity);
    @Override
    @Mapping(source = "parentId", target = "parent")
    public abstract Category dtoToModel(CategoryRequest request);
    public abstract CategoryShortResponse modelToShortDto(Category category);

    @Override
    public Category update(Category entity, CategoryRequest request) {
        if (request==null) return entity;
        if (request.getName()!=null && !request.getName().isBlank())
            entity.setName(request.getName());
        if (request.getDescription()!=null)
            entity.setDescription(request.getDescription());
        if (request.getParentId()!=null)
            entity.setParent(map(request.getParentId()));
        return entity;
    }

    protected Category map(Long value) {
        if (value == null) return null;
        Optional<Category> mayBeCategory = repository.findById(value);
        return mayBeCategory.orElse(null);
    }
}
