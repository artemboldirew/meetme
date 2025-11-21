package ru.vsu.cs.boldyrev.meetme.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_products")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Product product;
    private Integer quantity;
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
    private Order order;
}
