package com.nk.salesengineapi.infrastructure.persistence.mapper;

import com.nk.salesengineapi.domain.model.StoreModel;
import com.nk.salesengineapi.infrastructure.persistence.entity.StoreEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreEntityMapper {

    private final ModelMapper modelMapper;

    public StoreEntity toEntity(StoreModel model) {
        return modelMapper.map(model, StoreEntity.class);
    }

    public StoreModel toDomain(StoreEntity entity) {
        return modelMapper.map(entity, StoreModel.class);
    }

}
