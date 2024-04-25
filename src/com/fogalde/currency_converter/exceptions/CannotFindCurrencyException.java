package com.fogalde.currency_converter.exceptions;

public class CannotFindCurrencyException extends RuntimeException{
    private String message;

    public CannotFindCurrencyException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
