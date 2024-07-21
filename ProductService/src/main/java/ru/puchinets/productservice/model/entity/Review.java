package ru.puchinets.productservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "reviews", schema = "product_management")
public class Review {
    @Id
    @SequenceGenerator(name = "review_seq", sequenceName = "reviews_id_seq", schema = "product_management", allocationSize = 1)
    private Long id;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "user_id")
    private Long userId;
    private Integer rating;
    private String comment;
}
