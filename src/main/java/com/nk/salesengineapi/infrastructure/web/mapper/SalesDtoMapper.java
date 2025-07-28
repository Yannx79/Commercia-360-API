package com.nk.salesengineapi.infrastructure.web.mapper;

import com.nk.salesengineapi.application.dto.sales.SalesRequest;
import com.nk.salesengineapi.application.dto.sales.SalesResponse;
import com.nk.salesengineapi.domain.model.ProductModel;
import com.nk.salesengineapi.domain.model.SalesModel;
import com.nk.salesengineapi.domain.model.StoreModel;
import com.nk.salesengineapi.domain.model.TimeModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SalesDtoMapper {

    private final ModelMapper mapper;

    public SalesModel toDomain(SalesRequest request) {
        Set<TimeModel> times = request.getTimeIds()
                .stream()
                .map(id -> TimeModel.builder().id(id).build())
                .collect(Collectors.toSet());

        Set<StoreModel> stores = request.getStoreIds()
                .stream()
                .map(id -> StoreModel.builder().id(id).build())
                .collect(Collectors.toSet());

        Set<ProductModel> products = request.getProductIds()
                .stream()
                .map(id -> ProductModel.builder().id(id).build())
                .collect(Collectors.toSet());

        // return mapper.map(request, SalesModel.class);
        return SalesModel.builder()
                .times(times)
                .stores(stores)
                .products(products)
                .build();
    }

    public SalesResponse toResponse(SalesModel model) {
        return mapper.map(model, SalesResponse.class);
    }

}
