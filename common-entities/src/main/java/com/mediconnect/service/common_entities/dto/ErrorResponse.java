package com.mediconnect.service.common_entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

    private String errorCode;
    private String errorMessage;
    private int status;
    private String path;
    private LocalDateTime timestamp;
}
