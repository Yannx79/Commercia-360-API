package com.nk.salesengineapi.infrastructure.web.controller;

import com.nk.salesengineapi.application.dto.store.StoreRequest;
import com.nk.salesengineapi.application.dto.store.StoreResponse;
import com.nk.salesengineapi.application.port.in.StoreUseCase;
import com.nk.salesengineapi.domain.model.StoreModel;
import com.nk.salesengineapi.infrastructure.web.mapper.StoreDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreUseCase storeUseCase;
    private final StoreDtoMapper modelMapper;
    private final StoreDtoMapper storeDtoMapper;

    @PostMapping
    public ResponseEntity<StoreResponse> create(@RequestBody StoreRequest request) {
        StoreModel model = modelMapper.toDomain(request);
        StoreModel saved = storeUseCase.create(model);
        StoreResponse response = modelMapper.toResponse(saved);
        return ResponseEntity.created(URI.create("/api/stores/" + saved.getId())).body(response);
    }

    @GetMapping
    public ResponseEntity<List<StoreResponse>> getAll() {
        List<StoreModel> models = storeUseCase.getAll();
        List<StoreResponse> responses = models
                .stream()
                .map(modelMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreResponse> getById(@PathVariable Long id) {
        StoreModel model = storeUseCase.getById(id);
        StoreResponse productResponse = modelMapper.toResponse(model);
        return ResponseEntity.ok(productResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreResponse> update(@PathVariable Long id, @RequestBody StoreRequest request) {
        StoreModel model = storeDtoMapper.toDomain(request);
        StoreModel update = storeUseCase.update(id, model);
        StoreResponse response = modelMapper.toResponse(update);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        storeUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

}
