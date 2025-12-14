package ru.vsu.cs.boldyrev.shopik.dto.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CreateCategoryDTO {
    @JsonProperty("category_name")
    private String name;
}
