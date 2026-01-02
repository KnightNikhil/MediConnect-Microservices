package com.mediconnect.service.common_entities.service;


import com.mediconnect.service.common_entities.dto.UserDto;
import com.mediconnect.service.common_entities.entity.DiagnosisCentre;
import com.mediconnect.service.common_entities.entity.Doctor;
import com.mediconnect.service.common_entities.entity.Enums.Role;
import com.mediconnect.service.common_entities.entity.Patient;
import com.mediconnect.service.common_entities.entity.UserEntity;
import com.mediconnect.service.common_entities.exception.InvalidCredentialsException;
import com.mediconnect.service.common_entities.repository.DiagnosisCentreRepository;
import com.mediconnect.service.common_entities.repository.DoctorRepository;
import com.mediconnect.service.common_entities.repository.PatientRepository;
import com.mediconnect.service.common_entities.security.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Lazy;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final DiagnosisCentreRepository diagnosisCentreRepository;
    private final ModelMapper modelMapper;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository,
                           DiagnosisCentreRepository diagnosisCentreRepository, ModelMapper modelMapper, JWTService jwtService,
                           @Lazy AuthenticationManager authenticationManager, @Lazy PasswordEncoder passwordEncoder) { // ???
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.diagnosisCentreRepository = diagnosisCentreRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String loginUser(String role, HttpServletRequest request) {
        String encodedToken = request.getHeader("Authorization").split("Basic ")[1];
        byte[] decodedToken = Base64.getDecoder().decode(encodedToken);
        String credentials = new String(decodedToken, StandardCharsets.UTF_8);

        final String[] values = credentials.split(":", 2);
        String id = values[0];
        String password = values[1];
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    id, password));
        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException();
        }
        return jwtService.createJWTToken(id, role);

    }

    @Override
    public Object createNewUser(String role, UserDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity =
        switch (Role.valueOf(role.toUpperCase())){
            case PATIENT -> patientRepository.save(modelMapper.map(user, Patient.class));
            case DOCTOR -> doctorRepository.save(modelMapper.map(user, Doctor.class));
            case DIAGNOSIS_CENTRE -> diagnosisCentreRepository.save(modelMapper.map(user, DiagnosisCentre.class));
            default -> throw new IllegalStateException("Unexpected value: " + role);
        };
        return userEntity;

    }

    @Override
    public UserEntity getUser(UserEntity user) {
        return switch (user.getRole()){
            case PATIENT -> patientRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found"));
            case DOCTOR -> doctorRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found"));
            case DIAGNOSIS_CENTRE -> diagnosisCentreRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found"));
            default -> throw new IllegalStateException("Unexpected value: " + user.getRole());
        };
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Long id = Long.valueOf(username);

        Optional<? extends UserDetails> user =
                doctorRepository.findById(id)
                        .map(d -> (UserDetails) d)
                        .or(
                                () -> patientRepository.findById(id)
                                        .map(p -> (UserDetails) p)
                        )
                        .or(
                                () -> diagnosisCentreRepository.findById(id)
                                        .map(dc -> (UserDetails) dc)
                        );

        return user.orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
    }
}
