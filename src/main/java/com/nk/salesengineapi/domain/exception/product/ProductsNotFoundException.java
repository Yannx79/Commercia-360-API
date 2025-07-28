package com.nk.salesengineapi.domain.exception.product;

import com.nk.salesengineapi.domain.exception.NotFoundException;

import java.util.List;

public class ProductsNotFoundException extends NotFoundException {
    public ProductsNotFoundException(List<Long> ids) {
        super("Products with IDs " + ids + " not founds.");
    }
}
