package ru.vsu.cs.boldyrev.shopik.entity;

import jakarta.persistence.*;
import ru.vsu.cs.boldyrev.shopik.enums.OrderStatus;
import ru.vsu.cs.boldyrev.shopik.enums.PaymentStatus;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 11, scale = 2, nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
