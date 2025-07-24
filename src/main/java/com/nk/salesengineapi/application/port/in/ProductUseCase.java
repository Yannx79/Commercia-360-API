package com.nk.salesengineapi.application.port.in;

import com.nk.salesengineapi.domain.model.ProductModel;

import java.util.List;

// En proyectos medianos y grandes es recomendable separa cada metodo en una interfaz
public interface ProductUseCase {
    ProductModel create(ProductModel product);
    List<ProductModel> getAll();
    ProductModel getById(Long id);
    ProductModel update(Long id, ProductModel product);
    void delete(Long id);
}
