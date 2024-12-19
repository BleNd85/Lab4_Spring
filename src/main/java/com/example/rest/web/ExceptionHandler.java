package com.example.rest.web;

import com.example.rest.service.exception.HotelNotFoundException;
import com.example.rest.service.exception.ParamsViolationException;
import lombok.NonNull;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.List;

import static java.net.URI.create;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleProductNotFoundException(HotelNotFoundException ex) {
        ProblemDetail problemDetail = forStatusAndDetail(NOT_FOUND, ex.getMessage());
        problemDetail.setType(create("hotel-not-found"));
        problemDetail.setTitle("Hotel Not Found");
        return ResponseEntity.status(NOT_FOUND).body(problemDetail);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        List<ParamsViolationException> validationResponse =
                errors.stream().map(err -> ParamsViolationException.builder().reason(err.getDefaultMessage()).fieldName(err.getField()).build()).toList();
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(getValidationProblemDetail(validationResponse));
    }


    public static ProblemDetail getValidationProblemDetail(List<ParamsViolationException> validationResponse) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "Request validation failed");
        problemDetail.setType(URI.create("validation-error"));
        problemDetail.setTitle("Field Validation Exception");
        problemDetail.setProperty("invalidParams", validationResponse);
        return problemDetail;
    }
}

