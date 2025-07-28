package com.nk.salesengineapi.infrastructure.web.controller;

import com.nk.salesengineapi.infrastructure.web.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IGenericRestController<TRequest, TResponse, ID> {
    ResponseEntity<ApiResponse<TResponse>> create(@RequestBody TRequest request);
    ResponseEntity<ApiResponse<List<TResponse>>> getAll();
    ResponseEntity<ApiResponse<TResponse>> getById(@PathVariable ID id);
    ResponseEntity<ApiResponse<TResponse>> update(@PathVariable ID id, @RequestBody TRequest request);
    ResponseEntity<Void> delete(@PathVariable ID id);
}
