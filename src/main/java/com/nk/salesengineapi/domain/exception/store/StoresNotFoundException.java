package com.nk.salesengineapi.domain.exception.store;

import com.nk.salesengineapi.domain.exception.NotFoundException;

import java.util.List;

public class StoresNotFoundException extends NotFoundException {
    public StoresNotFoundException(List<Long> ids) {
        super("Stores with IDs " + ids + " not founds.");
    }
}
