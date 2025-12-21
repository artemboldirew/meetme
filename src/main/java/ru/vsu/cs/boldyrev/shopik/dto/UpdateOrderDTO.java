package ru.vsu.cs.boldyrev.shopik.dto;

import lombok.Getter;
import ru.vsu.cs.boldyrev.shopik.dictionary.OrderStatus;

/**
 * DTO для частичного обновления заказа.
 * <p>
 * Позволяет изменять статус заказа ({@link OrderStatus}).
 * </p>
 */
@Getter
public class UpdateOrderDTO {

    /** Новый статус заказа */
    private OrderStatus orderStatus;
}
