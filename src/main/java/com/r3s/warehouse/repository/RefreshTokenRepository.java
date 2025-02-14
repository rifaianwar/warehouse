package com.r3s.warehouse.repository;


import com.r3s.warehouse.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
        Optional<RefreshToken> findByToken(String token);
}
