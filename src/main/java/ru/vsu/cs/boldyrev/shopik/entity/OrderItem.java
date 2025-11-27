package ru.vsu.cs.boldyrev.shopik.entity;

import jakarta.persistence.*;
import ru.vsu.cs.boldyrev.shopik.enums.OrderItemStatus;
import ru.vsu.cs.boldyrev.shopik.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(precision = 11, scale = 2, nullable = false)
    private BigDecimal amount;

    private Integer quantity;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_status")
    private OrderItemStatus orderItemStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id")
    private Order order;
}
