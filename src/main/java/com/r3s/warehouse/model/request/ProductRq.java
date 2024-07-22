package com.r3s.warehouse.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRq {
    @NotBlank
    @NotNull
    private String name;
    private String description;
    @NotBlank
    @NotNull
    private int quantity;
    @NotBlank
    @NotNull
    private ProductTypeRequest productTypeId;

    @Setter
    @Getter
    public static class ProductTypeRequest {
        private Long id;
    }
}
