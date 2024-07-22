package com.r3s.warehouse.repository;

import com.r3s.warehouse.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> getAllByOrderByNameAsc();

    @Query(value = "DELETE FROM ProductEntity c WHERE c.id =?1")
    void deleteById(Long id);
}
