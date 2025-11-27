package ru.vsu.cs.boldyrev.shopik.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProductAmount {
    @JsonProperty("productId")
    private Long productId;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("quantity")
    private Integer quantity;
}
