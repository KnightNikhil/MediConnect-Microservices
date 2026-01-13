package com.mediconnect.service.common_entities.exception;

import com.mediconnect.service.common_entities.entity.Enums.Role;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String message, Role role) {
        super(message, HttpStatus.NOT_FOUND, role.name() + "NOT FOUND" );
    }
}
