package com.r3s.warehouse.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product_type")
//@JsonIgnoreProperties(value = { "createdDate", "updatedDate", "deletedDate"})
//@SQLRestriction(value = "deleted_date is null")
public class ProductTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_product_type")
    @SequenceGenerator(name = "sq_product_type", sequenceName = "sq_product_type", allocationSize = 1)
    private Long id;
//    @Column(name = "created_date")
//    private Date createdDate;
//    @Column(name = "updated_date")
//    private Date updatedDate;
//    @Column(name = "deleted_date")
//    private Date deletedDate;
    @Column(unique = true, nullable = false)
    private String name;
}
