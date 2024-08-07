package ru.puchinets.productservice.model.dto.request;

import lombok.Data;

@Data
public class ReviewRequest {

    private Long productId;
    private Long userId;
    private Integer rating;
    private String comment;

}
