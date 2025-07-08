package com.practice.BookUrStay.Security;

import com.practice.BookUrStay.Model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Service
public class JwtTokenServiceImpl implements JwtTokenService {

//    @Value("${jwt.secret-key}")
//    private String secretKey;
//
//    private SecretKey getSecretKey() {
//        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
//    }

    MacAlgorithm alogorithm = Jwts.SIG.HS256;
    SecretKey secretKey = alogorithm.key().build();

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("email", user.getEmail())
                .claim("password", user.getPassword())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60))
                .signWith(secretKey, alogorithm)
                .compact();
    }

    @Override
    public String extractUsername(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            String username = extractUsername(token);
            Claims claims = getClaims(token);

            boolean isExpired = isTokenExpired(token);
//            if (isTokenExpired) {
//                log.warn("Token is expired");
//                return false;
//            }
            return username.equals(userDetails.getUsername()) && !isExpired;
        } catch (Exception e) {
            log.error("Token validation failed: {}", e.getMessage());
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}
