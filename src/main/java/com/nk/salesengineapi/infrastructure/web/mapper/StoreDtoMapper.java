package com.nk.salesengineapi.infrastructure.web.mapper;

import com.nk.salesengineapi.application.dto.store.StoreRequest;
import com.nk.salesengineapi.application.dto.store.StoreResponse;
import com.nk.salesengineapi.domain.model.StoreModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreDtoMapper {

    private final ModelMapper modelMapper;

    public StoreModel toDomain(StoreRequest request) {
        return modelMapper.map(request, StoreModel.class);
    }

    public StoreResponse toResponse(StoreModel model) {
        return modelMapper.map(model, StoreResponse.class);
    }

}
