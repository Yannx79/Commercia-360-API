package com.nk.salesengineapi.domain.exception.time;

import com.nk.salesengineapi.domain.exception.NotFoundException;

import java.util.List;

public class TimesNotFoundException extends NotFoundException {
    public TimesNotFoundException(List<Long> ids) {
        super("Times with IDs " + ids + " not founds.");
    }
}
