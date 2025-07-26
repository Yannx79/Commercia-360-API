package com.nk.salesengineapi.infrastructure.persistence.adapter;

import com.nk.salesengineapi.application.port.out.ProductRepositoryPort;
import com.nk.salesengineapi.domain.model.ProductModel;
import com.nk.salesengineapi.infrastructure.persistence.entity.ProductEntity;
import com.nk.salesengineapi.infrastructure.persistence.mapper.ProductEntityMapper;
import com.nk.salesengineapi.infrastructure.persistence.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository jpaRepository;
    private final ProductEntityMapper modelMapper;

    @Override
    public ProductModel save(ProductModel product) {
        ProductEntity entity = modelMapper.toEntity(product);
        ProductEntity saved = jpaRepository.save(entity);
        return modelMapper.toDomain(saved);
    }

    @Override
    public List<ProductModel> findAll() {
        return jpaRepository.findAll()
                .stream().map(modelMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductModel> findById(Long id) {
        return jpaRepository.findById(id)
                .map(modelMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
