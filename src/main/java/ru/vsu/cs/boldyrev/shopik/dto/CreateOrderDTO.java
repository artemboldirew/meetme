package ru.vsu.cs.boldyrev.shopik.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;


public class CreateOrderDTO {
    @JsonProperty("products")
    private List<ProductAmount> products;

    @Positive
    @NotNull
    @JsonProperty("amount")
    private BigDecimal amount;

    @NotBlank
    @JsonProperty("phone")
    private String phone;

    @NotBlank
    @JsonProperty("deliveryAddress")
    private String deliveryAddress;

    @NotNull
    @JsonProperty("deliveryLatitude")
    private BigDecimal deliveryLatitude;

    @NotNull
    @JsonProperty("deliveryLongitude")
    private BigDecimal deliveryLongitude;

    public List<ProductAmount> getProducts() {
        return products;
    }

    public @Positive @NotNull BigDecimal getAmount() {
        return amount;
    }

    public @NotBlank String getPhone() {
        return phone;
    }

    public @NotBlank String getDeliveryAddress() {
        return deliveryAddress;
    }

    public @NotNull BigDecimal getDeliveryLatitude() {
        return deliveryLatitude;
    }

    public @NotNull BigDecimal getDeliveryLongitude() {
        return deliveryLongitude;
    }
}
