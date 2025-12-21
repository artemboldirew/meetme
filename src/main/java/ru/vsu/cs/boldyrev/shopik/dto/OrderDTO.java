package ru.vsu.cs.boldyrev.shopik.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.vsu.cs.boldyrev.shopik.dictionary.OrderStatus;
import ru.vsu.cs.boldyrev.shopik.dictionary.PaymentStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * DTO для передачи информации о заказе.
 * <p>
 * Содержит основную информацию о заказе, статусах оплаты и заказа,
 * а также список позиций {@link OrderItemDTO}.
 * </p>
 */
@Data
@AllArgsConstructor
public class OrderDTO {

    /** Уникальный идентификатор заказа */
    private UUID id;

    /** Общая сумма заказа */
    private BigDecimal amount;

    /** Статус оплаты заказа */
    private PaymentStatus paymentStatus;

    /** Статус заказа */
    private OrderStatus orderStatus;

    /** Идентификатор клиента */
    private UUID customerId;

    /** Идентификатор пункта самовывоза */
    private UUID pickupPointId;

    /** Список позиций заказа */
    private List<OrderItemDTO> items;
}
