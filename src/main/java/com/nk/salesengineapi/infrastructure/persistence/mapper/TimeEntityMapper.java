package com.nk.salesengineapi.infrastructure.persistence.mapper;

import com.nk.salesengineapi.domain.model.TimeModel;
import com.nk.salesengineapi.infrastructure.persistence.entity.TimeEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TimeEntityMapper {

    private final ModelMapper modelMapper;

    public TimeEntity toEntity(TimeModel model) {
        return modelMapper.map(model, TimeEntity.class);
    }

    public TimeModel toDomain(TimeEntity entity) {
        return modelMapper.map(entity, TimeModel.class);
    }

}
