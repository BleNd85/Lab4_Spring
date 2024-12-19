package com.example.rest.service.exception;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ParamsViolationException {
    String fieldName;
    String reason;
}
