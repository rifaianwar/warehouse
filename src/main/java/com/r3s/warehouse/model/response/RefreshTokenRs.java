package com.r3s.warehouse.model.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RefreshTokenRs {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
}
