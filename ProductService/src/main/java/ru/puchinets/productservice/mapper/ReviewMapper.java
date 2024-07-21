package ru.puchinets.productservice.mapper;

import org.mapstruct.Mapper;
import ru.puchinets.productservice.model.dto.request.CategoryRequest;
import ru.puchinets.productservice.model.dto.request.ReviewRequest;
import ru.puchinets.productservice.model.dto.response.CategoryResponse;
import ru.puchinets.productservice.model.dto.response.ReviewResponse;
import ru.puchinets.productservice.model.entity.Category;
import ru.puchinets.productservice.model.entity.Review;

@Mapper(componentModel = "spring")
public interface ReviewMapper extends BaseMapper<ReviewRequest, ReviewResponse, Review> {

    @Override
    ReviewResponse modelToDto(Review entity);
    @Override
    Review dtoToModel(ReviewRequest request);

    @Override
    default Review update(Review entity, ReviewRequest request) {
        if (request==null) return entity;
        if (request.getComment()!=null && !request.getComment().isBlank())
            entity.setComment(request.getComment());
        return entity;
    }
}
