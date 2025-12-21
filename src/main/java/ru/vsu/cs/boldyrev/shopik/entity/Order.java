/**
 * Сущность заказа в интернет-магазине.
 * Содержит информацию о заказе, включая сумму, статусы оплаты и заказа,
 * данные покупателя, пункт выдачи и список товаров в заказе.
 *
 * @author Boldyrev
 * @version 1.0
 * @see Customer
 * @see PickupPoint
 * @see OrderItem
 * @see ru.vsu.cs.boldyrev.shopik.dictionary.OrderStatus
 * @see ru.vsu.cs.boldyrev.shopik.dictionary.PaymentStatus
 */
package ru.vsu.cs.boldyrev.shopik.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vsu.cs.boldyrev.shopik.dictionary.OrderStatus;
import ru.vsu.cs.boldyrev.shopik.dictionary.PaymentStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {
    /**
     * Уникальный идентификатор заказа
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Общая сумма заказа с точностью до 2 знаков после запятой (обязательное)
     */
    @Column(precision = 11, scale = 2, nullable = false)
    private BigDecimal amount;

    /**
     * Статус оплаты заказа
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    /**
     * Статус заказа
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    /**
     * Покупатель, сделавший заказ (обязательное)
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * Список товаров в заказе
     */
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OrderItem> items;

    /**
     * Пункт выдачи заказа (обязательное)
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pickup_point_id")
    private PickupPoint pickupPoint;
}