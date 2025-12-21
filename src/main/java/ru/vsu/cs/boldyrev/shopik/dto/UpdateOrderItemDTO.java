package ru.vsu.cs.boldyrev.shopik.dto;

import lombok.Getter;
import ru.vsu.cs.boldyrev.shopik.dictionary.OrderItemStatus;
import java.time.LocalDate;

/**
 * DTO для частичного обновления позиции заказа.
 * <p>
 * Позволяет изменять дату доставки и статус позиции ({@link OrderItemStatus}).
 * </p>
 */
@Getter
public class UpdateOrderItemDTO {

    /** Новая дата доставки для позиции заказа */
    private LocalDate deliveryDate;

    /** Новый статус позиции заказа */
    private OrderItemStatus orderItemStatus;
}
