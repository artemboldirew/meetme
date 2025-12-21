/**
 * Сущность позиции (товара) в заказе.
 * Содержит информацию о товаре, количестве, дате доставки и статусе позиции заказа.
 * Связана с сущностями Order и Product.
 *
 * @author Boldyrev
 * @version 1.0
 * @see Order
 * @see Product
 * @see ru.vsu.cs.boldyrev.shopik.dictionary.OrderItemStatus
 */
package ru.vsu.cs.boldyrev.shopik.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.vsu.cs.boldyrev.shopik.dictionary.OrderItemStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {
    /**
     * Уникальный идентификатор позиции заказа
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Товар, входящий в позицию заказа
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * Количество товара (обязательное)
     */
    @NotNull
    private Integer quantity;

    /**
     * Дата доставки товара
     */
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    /**
     * Статус позиции заказа
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "item_status")
    private OrderItemStatus orderItemStatus;

    /**
     * Заказ, к которому принадлежит позиция (обязательное)
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    /**
     * Дата и время создания записи (автоматически устанавливается)
     */
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /**
     * Дата и время последнего обновления записи (автоматически обновляется)
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}