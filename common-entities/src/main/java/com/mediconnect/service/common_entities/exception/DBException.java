package com.mediconnect.service.common_entities.exception;

import org.springframework.http.HttpStatus;

public class DBException extends BaseException {
    public DBException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR, "DB_ERROR");
    }
}
