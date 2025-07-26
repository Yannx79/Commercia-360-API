package com.nk.salesengineapi.domain.exception.sales;

import com.nk.salesengineapi.domain.exception.NotFoundException;

public class SalesNotFoundException extends NotFoundException {
    public SalesNotFoundException(Long id) {
        super("Sales with ID " + id + " not found.");
    }
}
