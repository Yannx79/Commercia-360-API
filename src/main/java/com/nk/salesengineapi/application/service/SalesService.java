package com.nk.salesengineapi.application.service;

import com.nk.salesengineapi.application.port.in.SalesUseCase;
import com.nk.salesengineapi.application.port.out.ProductRepositoryPort;
import com.nk.salesengineapi.application.port.out.SalesRepositoryPort;
import com.nk.salesengineapi.application.port.out.StoreRepositoryPort;
import com.nk.salesengineapi.application.port.out.TimeRepositoryPort;
import com.nk.salesengineapi.domain.exception.product.ProductsNotFoundException;
import com.nk.salesengineapi.domain.exception.sales.SalesNotFoundException;
import com.nk.salesengineapi.domain.exception.store.StoresNotFoundException;
import com.nk.salesengineapi.domain.exception.time.TimesNotFoundException;
import com.nk.salesengineapi.domain.model.ProductModel;
import com.nk.salesengineapi.domain.model.SalesModel;
import com.nk.salesengineapi.domain.model.StoreModel;
import com.nk.salesengineapi.domain.model.TimeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService implements SalesUseCase {

    private final SalesRepositoryPort repositoryPort;
    private final TimeRepositoryPort timeRepo;
    private final StoreRepositoryPort storeRepo;
    private final ProductRepositoryPort productRepo;

    @Override
    public SalesModel create(SalesModel model) {
        List<Long> timeIds = model.getTimes().stream().map(TimeModel::getId).toList();
        List<TimeModel> foundTimes = timeRepo.findAllById(timeIds);
        if (foundTimes.size() != timeIds.size()) {
            throw new TimesNotFoundException(timeIds);
        }
        model.setTimes(new HashSet<>(foundTimes));

        List<Long> storeIds = model.getStores().stream().map(StoreModel::getId).toList();
        List<StoreModel> foundStores = storeRepo.findAllById(storeIds);
        if (foundStores.size() != storeIds.size()) {
            throw new StoresNotFoundException(storeIds);
        }
        model.setStores(new HashSet<>(foundStores));

        List<Long> productIds = model.getProducts().stream().map(ProductModel::getId).toList();
        List<ProductModel> foundProducts = productRepo.findAllById(productIds);
        if (foundProducts.size() != productIds.size()) {
            throw new ProductsNotFoundException(productIds);
        }
        model.setProducts(new HashSet<>(foundProducts));

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
