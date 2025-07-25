package com.nk.salesengineapi.application.port.out;

import java.util.List;
import java.util.Optional;

public interface IGenericRepositoryPort<Model, ID> {
    
    Model save(Model product);
    List<Model> findAll();
    Optional<Model> findById(ID id);
    void deleteById(ID id);

}
