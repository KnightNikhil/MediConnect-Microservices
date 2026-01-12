package com.mediconnect.service.common_entities.service;

import com.mediconnect.service.common_entities.dto.LoginResponseDto;
import com.mediconnect.service.common_entities.entity.RefreshToken;
import com.mediconnect.service.common_entities.entity.UserEntity;

public interface AuthService {

    public boolean saveRefreshToken(RefreshToken refreshToken);
    public RefreshToken getRefreshTokenData(String token);

    UserEntity getUserById(Long id);
}
