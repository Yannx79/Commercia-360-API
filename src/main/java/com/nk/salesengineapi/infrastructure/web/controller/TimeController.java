package com.nk.salesengineapi.infrastructure.web.controller;

import com.nk.salesengineapi.application.dto.time.TimeRequest;
import com.nk.salesengineapi.application.dto.time.TimeResponse;
import com.nk.salesengineapi.application.port.in.TimeUseCase;
import com.nk.salesengineapi.domain.model.TimeModel;
import com.nk.salesengineapi.infrastructure.web.mapper.TimeDtoMapper;
import com.nk.salesengineapi.infrastructure.web.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/times")
@RequiredArgsConstructor
public class TimeController implements  IGenericRestController<TimeRequest, TimeResponse, Long> {

    private final TimeUseCase timeUseCase;
    private final TimeDtoMapper modelMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<TimeResponse>> create(@RequestBody TimeRequest request) {
        TimeModel model = modelMapper.toDomain(request);
        TimeModel saved = timeUseCase.create(model);
        TimeResponse response = modelMapper.toResponse(saved);
        return ResponseEntity.created(URI.create("/api/times/" + saved.getId())).body(ApiResponse.success(response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TimeResponse>>> getAll() {
        List<TimeModel> models = timeUseCase.getAll();
        List<TimeResponse> responses = models
                .stream()
                .map(modelMapper::toResponse)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(responses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TimeResponse>> getById(@PathVariable Long id) {
        TimeModel model = timeUseCase.getById(id);
        TimeResponse timeResponse = modelMapper.toResponse(model);
        return ResponseEntity.ok(ApiResponse.success(timeResponse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TimeResponse>> update(@PathVariable Long id, @RequestBody TimeRequest request) {
        TimeModel model = modelMapper.toDomain(request);
        TimeModel update = timeUseCase.update(id, model);
        TimeResponse response = modelMapper.toResponse(update);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        timeUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

}
