package com.nk.salesengineapi.infrastructure.web.controller;

import com.nk.salesengineapi.application.dto.ProductRequest;
import com.nk.salesengineapi.application.dto.ProductResponse;
import com.nk.salesengineapi.application.port.in.ProductUseCase;
import com.nk.salesengineapi.domain.model.ProductModel;
import com.nk.salesengineapi.infrastructure.web.mapper.ProductDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductUseCase productUseCase;
    private final ProductDtoMapper modelMapper;
    private final ProductDtoMapper productDtoMapper;

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest request) {
        ProductModel model = modelMapper.toDomain(request);
        ProductModel saved = productUseCase.create(model);
        ProductResponse response = modelMapper.toResponse(saved);
        return ResponseEntity.created(URI.create("/api/products/" + saved.getId())).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        List<ProductModel> models = productUseCase.getAll();
        List<ProductResponse> responses = models
                .stream()
                .map(modelMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        ProductModel model = productUseCase.getById(id);
        ProductResponse productResponse = modelMapper.toResponse(model);
        return ResponseEntity.ok(productResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody ProductRequest request) {
        ProductModel model = productDtoMapper.toDomain(request);
        ProductModel update = productUseCase.update(id, model);
        ProductResponse response = modelMapper.toResponse(update);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

}
