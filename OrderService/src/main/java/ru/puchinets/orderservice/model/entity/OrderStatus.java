package ru.puchinets.orderservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "next_status_id")
    private Short nextStatusId;
    @Column(name = "previous_status_id")
    private Short previousStatusId;
}
