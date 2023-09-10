package com.example.besttravel.exception;

public class IdNotFoundException extends RuntimeException{

    private static final String ERROR_MESAGGE="Record no exist in %s";

    public IdNotFoundException(String tableName){
        super(String.format(ERROR_MESAGGE,tableName));
    }
}
