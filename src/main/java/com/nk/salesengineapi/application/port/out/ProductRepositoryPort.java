package com.nk.salesengineapi.application.port.out;

import com.nk.salesengineapi.domain.model.ProductModel;

import java.util.List;
import java.util.Optional;

// En proyectos grandes o medianos es mejor separa los metodos en interfaz propias
public interface ProductRepositoryPort {
    ProductModel save(ProductModel product);
    List<ProductModel> findAll();
    Optional<ProductModel> findById(Long id);
    void deleteById(Long id);
}
