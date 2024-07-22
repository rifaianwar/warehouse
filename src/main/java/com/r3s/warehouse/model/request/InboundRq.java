package com.r3s.warehouse.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InboundRq {
    private int quantity;
    private String source;
    private ProductRequest product;

    @Setter
    @Getter
    public static class ProductRequest {
        private Long id;
    }
}
