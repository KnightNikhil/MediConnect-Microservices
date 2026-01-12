package com.mediconnect.service.common_entities.service;

import com.mediconnect.service.common_entities.repository.RefreshTokenRepository;
import com.mediconnect.service.common_entities.entity.RefreshToken;
import com.mediconnect.service.common_entities.entity.UserEntity;
import com.mediconnect.service.common_entities.repository.DiagnosisCentreRepository;
import com.mediconnect.service.common_entities.repository.DoctorRepository;
import com.mediconnect.service.common_entities.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final DiagnosisCentreRepository diagnosisCentreRepository;

    @Override
    public boolean saveRefreshToken(RefreshToken refreshToken) {
        //TODO:: how to see if it actually saved or not
        refreshTokenRepository.save(refreshToken);
        return true;
    }

    @Override
    public RefreshToken getRefreshTokenData(String token) {
        return refreshTokenRepository.findByRefreshToken(token).orElseThrow(() -> new RuntimeException("Refresh token not found"));

    }

    @Override
    public UserEntity getUserById(Long id) throws UsernameNotFoundException {
        Optional<? extends UserEntity> user =
                doctorRepository.findById(id)
                        .map(d -> (UserEntity) d)
                        .or(
                                () -> patientRepository.findById(id)
                                        .map(p -> (UserEntity) p)
                        )
                        .or(
                                () -> diagnosisCentreRepository.findById(id)
                                        .map(dc -> (UserEntity) dc)
                        );

        return user.orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
    }
}
