package ru.puchinets.productservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "categories", schema = "product_management")
public class Category extends BaseEntity {
    @Id
    @SequenceGenerator(name = "category_seq", sequenceName = "categories_id_seq", schema = "product_management", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    private Long id;
    private String name;
    private String description;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;
}
