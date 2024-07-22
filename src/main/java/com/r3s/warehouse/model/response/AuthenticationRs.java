package com.r3s.warehouse.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AuthenticationRs {
    private Long id;
    private List<String> roles;
    private String accessToken;
    private String refreshToken;
    private String tokenType;
}
