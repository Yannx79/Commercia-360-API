package com.nk.salesengineapi.domain.exception.time;

import com.nk.salesengineapi.domain.exception.NotFoundException;

public class TimeNotFoundException extends NotFoundException {
    public TimeNotFoundException(Long id) {
        super("Store with ID " + id + " not found.");
    }
}
