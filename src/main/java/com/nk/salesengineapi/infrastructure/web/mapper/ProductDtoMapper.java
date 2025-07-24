package com.nk.salesengineapi.infrastructure.web.mapper;

import com.nk.salesengineapi.application.dto.ProductRequest;
import com.nk.salesengineapi.application.dto.ProductResponse;
import com.nk.salesengineapi.domain.model.ProductModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductDtoMapper {

    private final ModelMapper modelMapper;

    public ProductModel toDomain(ProductRequest dto) {
        return modelMapper.map(dto, ProductModel.class);
    }

    public ProductResponse toResponse(ProductModel model) {
        return modelMapper.map(model, ProductResponse.class);
    }

}
