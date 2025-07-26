package com.nk.salesengineapi.application.port.in;

import com.nk.salesengineapi.domain.model.ProductModel;

// In medium and large projects, it's recommended to separate each method into its own interface.
public interface ProductUseCase extends IGenericUseCase<ProductModel, Long> {
}
