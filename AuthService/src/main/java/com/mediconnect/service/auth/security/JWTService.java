package com.mediconnect.service.auth.security;

import com.mediconnect.service.auth.service.AuthService;
import com.mediconnect.service.common_entities.dto.LoginResponseDto;
import com.mediconnect.service.common_entities.dto.UserDto;
import com.mediconnect.service.common_entities.entity.*;
import com.mediconnect.service.common_entities.entity.Enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JWTService {

    private final AuthService authService;
    private String SECRET_KEY = "mediconnectsecretkeymediconnectsecretkeymediconnectsecretkeymediconnectsecretkey";

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public LoginResponseDto createJWTAccessToken(String username, String role){
        String accessToken = Jwts.builder()
                .id(username)
                .signWith(getSecretKey())
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 ))
                .compact();

        String refreshToken = createRefreshToken();
        boolean isSaved = authService.saveRefreshToken(RefreshToken.builder()
                        .refreshToken(refreshToken)
                        .userId(Long.valueOf(username))
                        .expirationTime(LocalDateTime.now().plusDays(1))
                .build());

        return LoginResponseDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }

    private String createRefreshToken(){
        return UUID.randomUUID().toString();
    }

    public LoginResponseDto getAccessTokenFromRefresh(String token) {
        Long id;
        RefreshToken refreshToken = authService.getRefreshTokenData(token);
        if (refreshToken.getExpirationTime().isAfter(LocalDateTime.now())) {
            id = refreshToken.getUserId();
            UserEntity user = authService.getUserById(id);
            return createJWTAccessToken(id.toString(), user.getRole().toString());

        }
        return null;
    }


    public UserEntity getUserFromToken(String token){
        JwtParser jwtparser = Jwts.parser().verifyWith(getSecretKey()).build();

        Claims claims = jwtparser.parseSignedClaims(token)
                .getPayload();

        Role role = Role.valueOf(claims.get("role").toString().toUpperCase());
        UserEntity user = null;
        user= switch (role){
            case Role.PATIENT -> new Patient();
            case Role.DOCTOR -> new Doctor();
            case Role.DIAGNOSIS_CENTRE -> new DiagnosisCentre();
            default -> throw new IllegalStateException("Unexpected value: " + claims.get("role"));
        };
        user.setId(Long.valueOf(claims.getId()));
        user.setRole(role);
        return user;

    }
}
