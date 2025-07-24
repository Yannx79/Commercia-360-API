package com.nk.salesengineapi.domain.exception.product;

import com.nk.salesengineapi.domain.exception.NotFoundException;

public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException(Long id) {
      super("Product with ID " + id + " not found.");
    }
}
