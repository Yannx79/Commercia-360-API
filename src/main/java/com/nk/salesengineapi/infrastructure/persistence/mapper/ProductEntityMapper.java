package com.nk.salesengineapi.infrastructure.persistence.mapper;

import com.nk.salesengineapi.domain.model.ProductModel;
import com.nk.salesengineapi.infrastructure.persistence.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductEntityMapper {

    private final ModelMapper modelMapper;

    public ProductEntity toEntity(ProductModel model) {
        return modelMapper.map(model, ProductEntity.class);
    }

    public ProductModel toDomain(ProductEntity entity) {
        return modelMapper.map(entity, ProductModel.class);
    }

}
