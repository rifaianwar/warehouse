package com.r3s.warehouse.repository;

import com.r3s.warehouse.entity.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Long> {
    ProductTypeEntity findByName(String name);
    List<ProductTypeEntity> getAllByOrderByNameAsc();
}
