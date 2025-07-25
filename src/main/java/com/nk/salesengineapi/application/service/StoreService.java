package com.nk.salesengineapi.application.service;

import com.nk.salesengineapi.application.port.in.StoreUseCase;
import com.nk.salesengineapi.application.port.out.StoreRepositoryPort;
import com.nk.salesengineapi.domain.exception.store.StoreNotFoundException;
import com.nk.salesengineapi.domain.model.StoreModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService implements StoreUseCase {

    private final StoreRepositoryPort repositoryPort;

    @Override
    public StoreModel create(StoreModel model) {
        return repositoryPort.save(model);
    }

    @Override
    public List<StoreModel> getAll() {
        return repositoryPort.findAll();
    }

    @Override
    public StoreModel getById(Long id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new StoreNotFoundException(id));
    }

    @Override
    public StoreModel update(Long id, StoreModel model) {
        getById(id);
        model.setId(id);
        return repositoryPort.save(model);
    }

    @Override
    public void delete(Long id) {
        repositoryPort.deleteById(id);
    }
}
