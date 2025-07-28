package com.nk.salesengineapi.infrastructure.persistence.adapter;

import com.nk.salesengineapi.application.port.out.SalesRepositoryPort;
import com.nk.salesengineapi.domain.model.SalesModel;
import com.nk.salesengineapi.infrastructure.persistence.entity.SalesEntity;
import com.nk.salesengineapi.infrastructure.persistence.mapper.SalesEntityMapper;
import com.nk.salesengineapi.infrastructure.persistence.repository.SalesJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SalesRepositoryAdapter implements SalesRepositoryPort {

    private final SalesJpaRepository jpaRepository;
    private final SalesEntityMapper mapper;

    @Override
    public SalesModel save(SalesModel model) {
        SalesEntity salesEntity = mapper.toEntity(model);
        SalesEntity saved = jpaRepository.save(salesEntity);
        return mapper.toDomain(saved);
    }

    @Override
    public List<SalesModel> findAllById(List<Long> ids) {
        List<SalesEntity> list = jpaRepository.findAllById(ids);
        return list
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalesModel> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SalesModel> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
