package com.nk.salesengineapi.application.port.out;

import com.nk.salesengineapi.domain.model.TimeModel;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IGenericRepositoryPort<Model, ID> {
    
    Model save(Model product);
    List<Model> findAll();
    List<Model> findAllById(List<ID> ids);
    Optional<Model> findById(ID id);
    void deleteById(ID id);

}
