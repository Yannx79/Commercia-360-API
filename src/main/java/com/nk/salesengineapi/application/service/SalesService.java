package com.nk.salesengineapi.application.service;

import com.nk.salesengineapi.application.port.in.SalesUseCase;
import com.nk.salesengineapi.application.port.out.ProductRepositoryPort;
import com.nk.salesengineapi.application.port.out.SalesRepositoryPort;
import com.nk.salesengineapi.application.port.out.StoreRepositoryPort;
import com.nk.salesengineapi.application.port.out.TimeRepositoryPort;
import com.nk.salesengineapi.domain.exception.sales.SalesNotFoundException;
import com.nk.salesengineapi.domain.model.ProductModel;
import com.nk.salesengineapi.domain.model.SalesModel;
import com.nk.salesengineapi.domain.model.StoreModel;
import com.nk.salesengineapi.domain.model.TimeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalesService implements SalesUseCase {

    private final SalesRepositoryPort repositoryPort;
    private final TimeRepositoryPort timeRepo;
    private final StoreRepositoryPort storeRepo;
    private final ProductRepositoryPort productRepo;

    @Override
    public SalesModel create(SalesModel model) {
        model.setTimes(
                new HashSet<>(
                        timeRepo.findAllById(
                                model.getTimes()
                                        .stream()
                                        .map(TimeModel::getId)
                                        .toList()
                ))
        );
        model.setStores(
                new HashSet<>(
                        storeRepo.findAllById(
                                model.getStores()
                                        .stream()
                                        .map(StoreModel::getId)
                                        .toList()
                        )
                )
        );
        model.setProducts(
                new HashSet<>(
                        productRepo.findAllById(
                                model.getProducts()
                                        .stream()
                                        .map(ProductModel::getId)
                                        .toList()
                        )
                )
        );
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
