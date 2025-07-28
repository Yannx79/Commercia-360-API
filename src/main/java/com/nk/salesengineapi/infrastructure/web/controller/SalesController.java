package com.nk.salesengineapi.infrastructure.web.controller;

import com.nk.salesengineapi.application.dto.sales.SalesRequest;
import com.nk.salesengineapi.application.dto.sales.SalesResponse;
import com.nk.salesengineapi.application.port.in.SalesUseCase;
import com.nk.salesengineapi.domain.model.SalesModel;
import com.nk.salesengineapi.infrastructure.web.mapper.SalesDtoMapper;
import com.nk.salesengineapi.infrastructure.web.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SalesController implements IGenericRestController<SalesRequest, SalesResponse, Long> {

    private final SalesUseCase salesUseCase;
    private final SalesDtoMapper mapper;

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<SalesResponse>> create(SalesRequest salesRequest) {
        SalesModel model = mapper.toDomain(salesRequest);
        SalesModel saved = salesUseCase.create(model);
        SalesResponse response = mapper.toResponse(saved);
        return ResponseEntity.created(URI.create("/api/sales/" + saved.getId())).body(ApiResponse.success(response));
    }

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<List<SalesResponse>>> getAll() {
        List<SalesModel> models = salesUseCase.getAll();
        List<SalesResponse> responses = models
                .stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(responses));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SalesResponse>> getById(Long id) {
        SalesModel model = salesUseCase.getById(id);
        SalesResponse response = mapper.toResponse(model);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SalesResponse>> update(Long id, SalesRequest request) {
        SalesModel model = mapper.toDomain(request);
        SalesModel update = salesUseCase.update(id, model);
        SalesResponse response = mapper.toResponse(update);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Long id) {
        salesUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
