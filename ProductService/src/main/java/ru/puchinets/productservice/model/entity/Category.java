package ru.puchinets.productservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categories", schema = "product_management")
public class Category extends BaseEntity {
    @Id
    @SequenceGenerator(name = "category_seq", sequenceName = "category_id_seq", schema = "product_management")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    private Long id;
    private String name;
    private String description;
}
