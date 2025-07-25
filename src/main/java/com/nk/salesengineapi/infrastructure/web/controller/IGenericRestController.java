package com.nk.salesengineapi.infrastructure.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IGenericRestController<TRequest, TResponse, ID> {
    ResponseEntity<TResponse> create(@RequestBody TRequest request);
    ResponseEntity<List<TResponse>> getAll();
    ResponseEntity<TResponse> getById(@PathVariable ID id);
    ResponseEntity<TResponse> update(@PathVariable ID id, @RequestBody TRequest request);
    ResponseEntity<Void> delete(@PathVariable ID id);
}
