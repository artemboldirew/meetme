package ru.vsu.cs.boldyrev.meetme.entity;

import jakarta.persistence.*;
import ru.vsu.cs.boldyrev.meetme.enums.OrderStatus;
import ru.vsu.cs.boldyrev.meetme.enums.PaymentStatus;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
