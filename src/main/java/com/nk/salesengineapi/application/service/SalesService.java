package com.nk.salesengineapi.application.service;

import com.nk.salesengineapi.application.port.in.SalesUseCase;
import com.nk.salesengineapi.application.port.out.SalesRepositoryPort;
import com.nk.salesengineapi.domain.exception.sales.SalesNotFoundException;
import com.nk.salesengineapi.domain.model.SalesModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService implements SalesUseCase {

    private final SalesRepositoryPort repositoryPort;

    @Override
    public SalesModel create(SalesModel model) {
        return repositoryPort.save(model);
    }

    @Override
    public List<SalesModel> getAll() {
        return repositoryPort.findAll();
    }

    @Override
    public SalesModel getById(Long id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new SalesNotFoundException(id));
    }

    @Override
    public SalesModel update(Long id, SalesModel model) {
        getById(id);
        model.setId(id);
        return repositoryPort.save(model);
    }

    @Override
    public void delete(Long id) {
        repositoryPort.deleteById(id);
    }
}
