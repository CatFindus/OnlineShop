package ru.puchinets.orderservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_statuses", schema = "order_management")
public class OrderStatus implements Serializable {
    @Id
    private Short id;
    private String name;
    private String description;
    @OneToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "next_status_id")
    private OrderStatus nextStatus;
    @OneToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "previous_status_id")
    private OrderStatus previousStatus;

    @OneToMany
    @EqualsAndHashCode.Exclude
    private Set<Order> orders;

}
