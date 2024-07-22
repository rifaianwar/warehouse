package com.r3s.warehouse.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductTypeRq {
    @NotBlank
    @NotNull
    private String name;
}
