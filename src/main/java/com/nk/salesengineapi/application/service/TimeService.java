package com.nk.salesengineapi.application.service;

import com.nk.salesengineapi.application.port.in.TimeUseCase;
import com.nk.salesengineapi.application.port.out.TimeRepositoryPort;
import com.nk.salesengineapi.domain.exception.time.TimeNotFoundException;
import com.nk.salesengineapi.domain.model.TimeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeService implements TimeUseCase {

    private final TimeRepositoryPort repositoryPort;

    @Override
    public TimeModel create(TimeModel model) {
        return repositoryPort.save(model);
    }

    @Override
    public List<TimeModel> getAll() {
        return repositoryPort.findAll();
    }

    @Override
    public TimeModel getById(Long id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new TimeNotFoundException(id));
    }

    @Override
    public TimeModel update(Long id, TimeModel model) {
        getById(id);
        model.setId(id);
        return repositoryPort.save(model);
    }

    @Override
    public void delete(Long id) {
        repositoryPort.deleteById(id);
    }
}
