package com.mediconnect.service.auth.service;

import com.mediconnect.service.common_entities.dto.LoginResponseDto;
import com.mediconnect.service.common_entities.dto.UserDto;
import com.mediconnect.service.common_entities.entity.DiagnosisCentre;
import com.mediconnect.service.common_entities.entity.Doctor;
import com.mediconnect.service.common_entities.entity.Enums.Role;
import com.mediconnect.service.common_entities.entity.Patient;
import com.mediconnect.service.common_entities.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    public Object createNewUser(String role, UserDto user);

    public UserEntity getUser(UserEntity user);

    public LoginResponseDto loginUser(String role, HttpServletRequest request);

}
