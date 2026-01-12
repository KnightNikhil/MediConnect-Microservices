package com.mediconnect.service.common_entities.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

    @Id
    private Long userId;
    private String refreshToken;
    private LocalDateTime expirationTime;

}
