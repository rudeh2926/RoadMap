package com.example.java.global.security.jwt;

import com.example.java.domain.auth.domain.RefreshToken;
import com.example.java.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.java.global.security.jwt.dto.TokenResponse;
import com.example.java.global.security.jwt.exception.ExpiredJwtException;
import com.example.java.global.security.principle.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenResponse getToken(String accountId) {
        String accessToken = generateToken(accountId, jwtProperties.getAccessExp());
        String refreshToken = generateRefreshToken(accountId);
        long currentTimeMillis = System.currentTimeMillis();
        long expirationTime = currentTimeMillis + (jwtProperties.getAccessExp() * 1000);
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiredAt(expirationTime)
                .build();
    }

    public String generateRefreshToken(String accountId) {
        String newRefreshToken = generateToken(accountId, jwtProperties.getRefreshExp());
        long currentTimeMillis = System.currentTimeMillis();
        long expirationTime = currentTimeMillis + (jwtProperties.getRefreshExp() * 1000);
        refreshTokenRepository.save(
                RefreshToken.builder()
                        .accountId(accountId)
                        .refreshToken(newRefreshToken)
                        .refreshTokenTime(expirationTime)
                        .build()
        );
        return newRefreshToken;
    }

    private String generateToken(String accountId, long expiration) {
        return Jwts.builder().signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setSubject(accountId)
                .setHeaderParam("typ", "access")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        return parseToken(bearer);
    }

    public String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.replace("Bearer ", "");
        }
        return null;
    }

    public UsernamePasswordAuthenticationToken authorization(String token) {
        UserDetails userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getTokenSubject(String subject) {
        return getTokenBody(subject).getSubject();
    }

    private Claims getTokenBody(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw ExpiredJwtException.EXCEPTION;
        }
    }
}
