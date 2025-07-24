package com.nk.salesengineapi.domain.exception;

// se puede crear excepciones particulares para cada modelo
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
