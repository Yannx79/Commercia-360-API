package com.nk.salesengineapi.infrastructure.web.mapper;

import com.nk.salesengineapi.application.dto.time.TimeRequest;
import com.nk.salesengineapi.application.dto.time.TimeResponse;
import com.nk.salesengineapi.domain.model.TimeModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TimeDtoMapper {

    private final ModelMapper modelMapper;

    public TimeModel toDomain(TimeRequest request) {
        return modelMapper.map(request, TimeModel.class);
    }

    public TimeResponse toResponse(TimeModel timeModel) {
        return modelMapper.map(timeModel, TimeResponse.class);
    }

}
