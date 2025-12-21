package ru.vsu.cs.boldyrev.shopik.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import java.util.UUID;

/**
 * DTO для указания количества конкретного продукта.
 * <p>
 * Используется при создании заказа ({@link CreateOrderDTO}) для передачи списка товаров и их количества.
 * </p>
 */
@Getter
public class ProductAmount {

    /** Идентификатор продукта */
    @NotNull
    private UUID productId;

    /** Количество единиц товара */
    @NotNull
    private Integer quantity;
}
