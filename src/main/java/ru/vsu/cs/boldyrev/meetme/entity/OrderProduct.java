package ru.vsu.cs.boldyrev.meetme.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_products")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Product product;
    private Integer quantity;
    private Order order;
}
