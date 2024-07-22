package com.r3s.warehouse.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sq_product")
    @SequenceGenerator(name = "sq_product", sequenceName = "sq_product", allocationSize = 1)
    private Long id;
    private String name;
    private String description;
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "product_type_id", referencedColumnName = "id", nullable = false)
    private ProductTypeEntity productTypeEntity;

}
