package ru.vsu.cs.boldyrev.shopik.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class ProductAmount {
    @JsonProperty("product_id")
    private UUID productId;

    @JsonProperty("quantity")
    private Integer quantity;
}
