package com.nk.salesengineapi.domain.exception;

// se puede crear excepciones particulares para cada modelo
public class BusinessRuleException extends RuntimeException {
  public BusinessRuleException(String message) {
    super(message);
  }
}
