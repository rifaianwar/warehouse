package com.r3s.warehouse.model.request;

import com.r3s.warehouse.config.AppConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRq {
    @NotBlank(message = "username cannot be empty")
    @NotNull(message = "username cannot be null")
    @Size(min = 5, message = "Username Min 5 Character")
    private String username;
    @NotBlank(message = "Password cannot be empty")
    @NotNull(message = "Password cannot be null")
    @Size(min = 5, message = "Password Min 5 Character")
    private String password;
    private AppConstant.Role role;
}
