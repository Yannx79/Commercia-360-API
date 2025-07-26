package com.nk.salesengineapi.infrastructure.persistence.mapper;

import com.nk.salesengineapi.domain.model.SalesModel;
import com.nk.salesengineapi.infrastructure.persistence.entity.SalesEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SalesEntityMapper {

    private final ModelMapper modelMapper;

    public SalesEntity toEntity(SalesModel model) {
        return modelMapper.map(model, SalesEntity.class);
    }

    public SalesModel toDomain(SalesEntity entity) {
        return modelMapper.map(entity, SalesModel.class);
    }

}
