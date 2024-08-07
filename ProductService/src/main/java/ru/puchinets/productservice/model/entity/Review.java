package ru.puchinets.productservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "reviews", schema = "product_management")
public class Review extends BaseEntity {
    @Id
    @SequenceGenerator(name = "review_seq", sequenceName = "reviews_id_seq", schema = "product_management", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq")
    private Long id;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "user_id")
    private Long userId;
    private Integer rating;
    private String comment;
}
