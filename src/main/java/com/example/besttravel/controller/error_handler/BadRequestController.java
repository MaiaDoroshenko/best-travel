package com.example.besttravel.controller.error_handler;

import com.example.besttravel.exception.IdNotFoundException;
import com.example.besttravel.model.responses.BaseErrorResponse;
import com.example.besttravel.model.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;


@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)//con que tipo de status voy a responder
public class BadRequestController {

    @ExceptionHandler(IdNotFoundException.class)
    public BaseErrorResponse handleIdNotFound(IdNotFoundException exception) {

        return ErrorResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .build();
    }


    //Las excepciones que arroja cuando una validacion falla (Validaciones de Spring datelle)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseErrorResponse handleIdNotFound(MethodArgumentNotValidException exception) {
        var errors=new ArrayList<String>();
        exception.getAllErrors()
                .forEach(error->errors.add(error.getDefaultMessage()));

        return BaseErrorResponse.builder()
                .errorCode(errors)
                .status(HttpStatus.BAD_REQUEST.name())
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .build();

    }

}
