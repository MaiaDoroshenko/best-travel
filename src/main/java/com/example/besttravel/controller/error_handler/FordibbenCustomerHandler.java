package com.example.besttravel.controller.error_handler;

import com.example.besttravel.exception.ForbiddenCustomerException;
import com.example.besttravel.model.responses.BaseErrorResponse;
import com.example.besttravel.model.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.FORBIDDEN)
public class FordibbenCustomerHandler {
    @ExceptionHandler(ForbiddenCustomerException.class)
    public BaseErrorResponse firbiddenId(ForbiddenCustomerException exception) {
        return ErrorResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.FORBIDDEN.name())
                .errorCode(HttpStatus.FORBIDDEN.value())
                .build();
    }

}
