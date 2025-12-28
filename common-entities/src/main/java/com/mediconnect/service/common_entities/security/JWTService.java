package com.mediconnect.service.common_entities.security;

import com.mediconnect.service.common_entities.dto.UserDto;
import com.mediconnect.service.common_entities.entity.DiagnosisCentre;
import com.mediconnect.service.common_entities.entity.Doctor;
import com.mediconnect.service.common_entities.entity.Enums.Role;
import com.mediconnect.service.common_entities.entity.Patient;
import com.mediconnect.service.common_entities.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JWTService {

    private String SECRET_KEY = "mediconnectsecretkeymediconnectsecretkeymediconnectsecretkeymediconnectsecretkey";

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String createJWTToken(String username, String role){
        return Jwts.builder()
                .id(username)
                .signWith(getSecretKey())
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .compact();

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
