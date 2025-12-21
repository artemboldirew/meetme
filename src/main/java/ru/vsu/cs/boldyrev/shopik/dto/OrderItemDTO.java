package ru.vsu.cs.boldyrev.shopik.dto;

import lombok.Data;
import ru.vsu.cs.boldyrev.shopik.dictionary.OrderItemStatus;

import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO для передачи информации о позиции заказа.
 * <p>
 * Содержит идентификаторы позиции и продукта, количество,
 * дату доставки и статус позиции {@link OrderItemStatus}.
 * </p>
 */
@Data
public class OrderItemDTO {

    /** Уникальный идентификатор позиции заказа */
    private UUID id;

    /** Идентификатор продукта */
    private UUID productId;

    /** Количество единиц товара в позиции */
    private Integer quantity;

    /** Планируемая дата доставки для данной позиции */
    private LocalDate deliveryDate;

    /** Статус позиции заказа */
    private OrderItemStatus orderItemStatus;
}
