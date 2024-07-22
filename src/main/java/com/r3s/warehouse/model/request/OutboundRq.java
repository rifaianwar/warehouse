package com.r3s.warehouse.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OutboundRq {
    private int quantity;
    private String destination;
    private InboundRq.ProductRequest product;
}
