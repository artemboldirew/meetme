package ru.vsu.cs.boldyrev.shopik.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import java.util.List;
import java.util.UUID;

@Getter
public class CreateOrderDTO {

    /** Список товаров и их количество */
    @NotEmpty
    private List<ProductAmount> products;

    /** Идентификатор пункта самовывоза */
    @NotNull
    private UUID pickupPointId;

}
