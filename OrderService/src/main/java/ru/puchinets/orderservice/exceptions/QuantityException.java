package ru.puchinets.orderservice.exceptions;

public class QuantityException extends RuntimeException {

    public QuantityException(String message) {
        super(message);
    }
}
