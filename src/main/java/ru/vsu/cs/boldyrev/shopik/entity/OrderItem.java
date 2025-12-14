package ru.vsu.cs.boldyrev.shopik.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import ru.vsu.cs.boldyrev.shopik.dictionary.OrderItemStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product productId;

    @NotNull
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
