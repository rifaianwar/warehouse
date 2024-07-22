package com.r3s.warehouse.repository;

import com.r3s.warehouse.entity.OutboundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboundRepository extends JpaRepository<OutboundEntity, Long> {
}
