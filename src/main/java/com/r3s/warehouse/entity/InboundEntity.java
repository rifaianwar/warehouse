package com.r3s.warehouse.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "inbound")
public class InboundEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_inbound")
    @SequenceGenerator(name = "sq_inbound", sequenceName = "sq_inbound", allocationSize = 1)
    private Long id;
    private Date date;
    private int quantity;
    private String source;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private ProductEntity productEntity;
}
