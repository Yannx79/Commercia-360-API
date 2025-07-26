package com.nk.salesengineapi.application.port.out;

import com.nk.salesengineapi.domain.model.ProductModel;

// En proyectos grandes o medianos es mejor separa los metodos en interfaz propias
public interface ProductRepositoryPort extends IGenericRepositoryPort<ProductModel, Long> {
}
