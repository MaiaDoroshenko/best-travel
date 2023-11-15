package com.example.besttravel.exception;



public class ForbiddenCustomerException  extends RuntimeException{
    public ForbiddenCustomerException(){

        super("This customer is blocked");
    }
}
