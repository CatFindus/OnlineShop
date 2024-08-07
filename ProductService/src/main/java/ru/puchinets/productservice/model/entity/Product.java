package ru.puchinets.productservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@EqualsAndHashCode(of = "id", callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products", schema = "product_management")
@Entity
public class Product extends BaseEntity {
    @Id
    @SequenceGenerator(name = "product_seq", sequenceName = "products_id_seq", schema = "product_management", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    @Column(name = "quantity_in_stock")
    private Integer quantityInStock;
    @Column(name = "quantity_in_reserve")
    private Integer quantityInReserve;
    @Column(name = "quantity_to_ship")
    private Integer quantityToShip;
    @Column(name = "category_id")
    private Long categoryId;
}
