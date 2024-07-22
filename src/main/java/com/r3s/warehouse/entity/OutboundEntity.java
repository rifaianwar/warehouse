package com.r3s.warehouse.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "outbound")
public class OutboundEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_outbound")
    @SequenceGenerator(name = "sq_outbound", sequenceName = "sq_outbound", allocationSize = 1)
    private Long id;
    private Date date;
    private int quantity;
    private String destination;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private ProductEntity productEntity;
}
