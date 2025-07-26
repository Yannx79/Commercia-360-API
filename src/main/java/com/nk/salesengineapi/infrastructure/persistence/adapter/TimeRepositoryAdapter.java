package com.nk.salesengineapi.infrastructure.persistence.adapter;

import com.nk.salesengineapi.application.port.out.TimeRepositoryPort;
import com.nk.salesengineapi.domain.model.TimeModel;
import com.nk.salesengineapi.infrastructure.persistence.entity.TimeEntity;
import com.nk.salesengineapi.infrastructure.persistence.mapper.TimeEntityMapper;
import com.nk.salesengineapi.infrastructure.persistence.repository.TimeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TimeRepositoryAdapter implements TimeRepositoryPort {

    private final TimeJpaRepository jpaRepository;
    private final TimeEntityMapper modelMapper;

    @Override
    public TimeModel save(TimeModel product) {
        TimeEntity entity = modelMapper.toEntity(product);
        TimeEntity saved = jpaRepository.save(entity);
        return modelMapper.toDomain(saved);
    }

    @Override
    public List<TimeModel> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(modelMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TimeModel> findById(Long id) {
        return jpaRepository.findById(id)
                .map(modelMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
