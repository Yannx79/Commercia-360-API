package com.nk.salesengineapi.domain.exception.store;

import com.nk.salesengineapi.domain.exception.NotFoundException;

public class StoreNotFoundException extends NotFoundException {
    public StoreNotFoundException(Long id) {
        super("Store with ID " + id + " not found.");
    }
}
