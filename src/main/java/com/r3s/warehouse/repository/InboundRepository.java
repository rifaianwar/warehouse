package com.r3s.warehouse.repository;

import com.r3s.warehouse.entity.InboundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InboundRepository extends JpaRepository<InboundEntity, Long> {
}
