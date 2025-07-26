package com.nk.salesengineapi.infrastructure.web.mapper;

import com.nk.salesengineapi.application.dto.sales.SalesRequest;
import com.nk.salesengineapi.application.dto.sales.SalesResponse;
import com.nk.salesengineapi.domain.model.SalesModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SalesDtoMapper {

    private final ModelMapper mapper;

    public SalesModel toDomain(SalesRequest request) {
        return mapper.map(request, SalesModel.class);
    }

    public SalesResponse toResponse(SalesModel model) {
        return mapper.map(model, SalesResponse.class);
    }

}
