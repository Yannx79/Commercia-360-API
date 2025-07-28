package com.nk.salesengineapi.infrastructure.web.controller;

import com.nk.salesengineapi.application.dto.product.ProductRequest;
import com.nk.salesengineapi.application.dto.product.ProductResponse;
import com.nk.salesengineapi.application.port.in.ProductUseCase;
import com.nk.salesengineapi.domain.model.ProductModel;
import com.nk.salesengineapi.infrastructure.web.mapper.ProductDtoMapper;
import com.nk.salesengineapi.infrastructure.web.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController implements IGenericRestController<ProductRequest, ProductResponse, Long> {

    private final ProductUseCase productUseCase;
    private final ProductDtoMapper modelMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> create(@RequestBody ProductRequest request) {
        ProductModel model = modelMapper.toDomain(request);
        ProductModel saved = productUseCase.create(model);
        ProductResponse response = modelMapper.toResponse(saved);
        return ResponseEntity.created(URI.create("/api/products/" + saved.getId())).body(ApiResponse.success(response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAll() {
        List<ProductModel> models = productUseCase.getAll();
        List<ProductResponse> responses = models
                .stream()
                .map(modelMapper::toResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(responses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getById(@PathVariable Long id) {
        ProductModel model = productUseCase.getById(id);
        ProductResponse response = modelMapper.toResponse(model);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> update(@PathVariable Long id, @RequestBody ProductRequest request) {
        ProductModel model = modelMapper.toDomain(request);
        ProductModel update = productUseCase.update(id, model);
        ProductResponse response = modelMapper.toResponse(update);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

}
