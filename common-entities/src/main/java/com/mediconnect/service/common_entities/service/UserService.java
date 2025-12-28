package com.mediconnect.service.common_entities.service;

import com.mediconnect.service.common_entities.dto.UserDto;
import com.mediconnect.service.common_entities.entity.DiagnosisCentre;
import com.mediconnect.service.common_entities.entity.Doctor;
import com.mediconnect.service.common_entities.entity.Enums.Role;
import com.mediconnect.service.common_entities.entity.Patient;
import com.mediconnect.service.common_entities.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;

public interface UserService {

    public Object createNewUser(String role, UserDto user);

    public UserEntity getUser(UserEntity user);

    public String loginUser(String role, HttpServletRequest request);
}
