package com.nk.salesengineapi.application.service;

import com.nk.salesengineapi.application.port.in.ProductUseCase;
import com.nk.salesengineapi.application.port.out.ProductRepositoryPort;
import com.nk.salesengineapi.domain.exception.product.ProductNotFoundException;
import com.nk.salesengineapi.domain.model.ProductModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductUseCase {

    private final ProductRepositoryPort repositoryPort;

    @Override
    public ProductModel create(ProductModel product) {
        return repositoryPort.save(product);
    }

    @Override
    public List<ProductModel> getAll() {
        return repositoryPort.findAll();
    }

    @Override
    public ProductModel getById(Long id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public ProductModel update(Long id, ProductModel product) {
        getById(id);
        product.setId(id);
        return repositoryPort.save(product);
    }

    @Override
    public void delete(Long id) {
        repositoryPort.deleteById(id);
    }
}
