package ru.puchinets.orderservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "orders", schema = "order_management")
public class Order extends BaseEntity {
    @Id
    @SequenceGenerator(name = "order_seq", sequenceName = "order_id_seq", schema = "order_management")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @JoinColumn(name = "status_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ManyToOne
    private OrderStatus status;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @OneToMany
    private List<OrderItem> orderItems;
}
