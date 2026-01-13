package com.mediconnect.service.common_entities.exception;

import org.springframework.http.HttpStatus;

public class DataUnavailable extends BaseException {
    public DataUnavailable(String message) {
        super(message, HttpStatus.NOT_FOUND, "NO_DATA_AVAILABLE");
    }
}
