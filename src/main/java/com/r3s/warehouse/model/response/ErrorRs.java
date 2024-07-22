package com.r3s.warehouse.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorRs {
    private Instant timestamp;
    private HttpStatus status;
    private String message;
    private Object error;
}
