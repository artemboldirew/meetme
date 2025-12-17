package ru.vsu.cs.boldyrev.shopik.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import java.util.List;
import java.util.UUID;

@Getter
public class CreateOrderDTO {
    @JsonProperty("products")
    private List<ProductAmount> products;

    @NotBlank
    @JsonProperty("deliveryAddress")
    private UUID pickup_point_id;

}
