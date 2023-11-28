package com.example.java.domain.auth.domain.repository;

import com.example.java.domain.auth.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    boolean existsByAccountId(String accountId);
    Optional<RefreshToken> findByAccountId(String accountId);
}
