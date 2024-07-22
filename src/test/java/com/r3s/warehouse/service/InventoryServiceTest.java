package com.r3s.warehouse.service;

import com.r3s.warehouse.entity.ProductEntity;
import com.r3s.warehouse.entity.ProductTypeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class InventoryServiceTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void addProductType() {
        ProductTypeEntity productTypeEntity = new ProductTypeEntity();
        productTypeEntity.setId(1L);
        productTypeEntity.setName("FASHION");
        Assertions.assertEquals("FASHION", productTypeEntity.getName());
    }

    @Test
    void addProduct() {
        ProductTypeEntity productTypeEntity = new ProductTypeEntity();
        productTypeEntity.setId(1L);
        productTypeEntity.setName("FASHION");

        ProductEntity productEntity1 = new ProductEntity();
        productEntity1.setId(1L);
        productEntity1.setName("Jersey MU");
        productEntity1.setDescription("Jersey MU Tahun 2000");
        productEntity1.setQuantity(2);
        productEntity1.setProductTypeEntity(productTypeEntity);

        ProductEntity productEntity2 = new ProductEntity();
        productEntity2.setId(1L);
        productEntity2.setName("Jersey MU");
        productEntity2.setDescription("Jersey MU Tahun 2000");
        productEntity2.setQuantity(2);
        productEntity2.setProductTypeEntity(productTypeEntity);

        Assertions.assertEquals(productEntity1, productEntity2);
    }
}