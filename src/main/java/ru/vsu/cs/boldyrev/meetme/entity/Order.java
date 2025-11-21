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
    private BigDecimal amount;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private Customer customer;
}
