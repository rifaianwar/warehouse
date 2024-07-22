package com.r3s.warehouse.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationRq {
    @NotBlank
    @NotNull
    @Size(min = 5, message = "Min 5 Character")
    private String username;
    @NotBlank
    @NotNull
    @Size(min = 5, message = "Min 5 Character")
    private String password;
}
