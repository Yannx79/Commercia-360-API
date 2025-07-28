package com.nk.salesengineapi.infrastructure.web.controller;

import com.nk.salesengineapi.application.dto.store.StoreRequest;
import com.nk.salesengineapi.application.dto.store.StoreResponse;
import com.nk.salesengineapi.application.port.in.StoreUseCase;
import com.nk.salesengineapi.domain.model.StoreModel;
import com.nk.salesengineapi.infrastructure.web.mapper.StoreDtoMapper;
import com.nk.salesengineapi.infrastructure.web.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController implements IGenericRestController<StoreRequest, StoreResponse, Long> {

    private final StoreUseCase storeUseCase;
    private final StoreDtoMapper modelMapper;

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse<StoreResponse>> create(@RequestBody StoreRequest request) {
        StoreModel model = modelMapper.toDomain(request);
        StoreModel saved = storeUseCase.create(model);
        StoreResponse response = modelMapper.toResponse(saved);
        return ResponseEntity.created(URI.create("/api/stores/" + saved.getId())).body(ApiResponse.success(response));
    }

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<List<StoreResponse>>> getAll() {
        List<StoreModel> models = storeUseCase.getAll();
        List<StoreResponse> responses = models
                .stream()
                .map(modelMapper::toResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(responses));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StoreResponse>> getById(@PathVariable Long id) {
        StoreModel model = storeUseCase.getById(id);
        StoreResponse response = modelMapper.toResponse(model);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StoreResponse>> update(@PathVariable Long id, @RequestBody StoreRequest request) {
        StoreModel model = modelMapper.toDomain(request);
        StoreModel update = storeUseCase.update(id, model);
        StoreResponse response = modelMapper.toResponse(update);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        storeUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

}
