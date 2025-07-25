package com.nk.salesengineapi.application.port.in;

import java.util.List;

// Use in small projects
public interface IGenericUseCase<Model, ID> {

    Model create(Model model);
    List<Model> getAll();
    Model getById(ID id);
    Model update(ID id, Model model);
    void delete(ID id);

}
