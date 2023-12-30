package com.example.java.domain.auth.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@RedisHash
public class RefreshToken {

    @Id
    private String accountId;

    private String refreshToken;

    private Long refreshTokenTime;
}
