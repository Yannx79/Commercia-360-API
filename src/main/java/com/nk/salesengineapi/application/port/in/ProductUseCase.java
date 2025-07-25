package com.nk.salesengineapi.application.port.in;

import com.nk.salesengineapi.domain.model.ProductModel;

import java.util.List;

// En proyectos medianos y grandes es recomendable separa cada metodo en una interfaz
public interface ProductUseCase extends IGenericUseCase<ProductModel, Long> {
}
