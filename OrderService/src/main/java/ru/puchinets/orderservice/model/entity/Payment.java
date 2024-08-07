package ru.puchinets.orderservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments", schema = "order_management")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends BaseEntity{
    @Id
    @SequenceGenerator(name = "payment_seq", sequenceName = "payment_id_seq", schema = "order_management")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq")
    private Long id;
    @Column(name = "order_id")
    private Long orderId;
    private BigDecimal amount;
    @Column(name = "payment_status")
    private String paymentStatus;
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
}
