package com.nk.salesengineapi.infrastructure.persistence.adapter;

import com.nk.salesengineapi.application.port.out.StoreRepositoryPort;
import com.nk.salesengineapi.domain.model.StoreModel;
import com.nk.salesengineapi.infrastructure.persistence.entity.StoreEntity;
import com.nk.salesengineapi.infrastructure.persistence.mapper.StoreEntityMapper;
import com.nk.salesengineapi.infrastructure.persistence.repository.StoreJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StoreRespositoryAdapter implements StoreRepositoryPort {

    private final StoreJpaRepository jpaRepository;
    private final StoreEntityMapper modelMapper;

    @Override
    public StoreModel save(StoreModel model) {
        StoreEntity storeEntity = modelMapper.toEntity(model);
        StoreEntity saved = jpaRepository.save(storeEntity);
        return modelMapper.toDomain(saved);
    }

    @Override
    public List<StoreModel> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(modelMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StoreModel> findById(Long id) {
        return jpaRepository.findById(id)
                .map(modelMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
