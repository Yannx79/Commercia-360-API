package com.nk.salesengineapi.application.service;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class SalesServiceTest {

    private SalesRepositoryPort salesRepo;
    private TimeRepositoryPort timeRepo;
    private StoreRepositoryPort storeRepo;
    private ProductRepositoryPort productRepo;
    private SalesService service;

    @BeforeEach
    void setUp() {
        salesRepo = mock(SalesRepositoryPort.class);
        timeRepo = mock(TimeRepositoryPort.class);
        storeRepo = mock(StoreRepositoryPort.class);
        productRepo = mock(ProductRepositoryPort.class);
        service = new SalesService(salesRepo, timeRepo, storeRepo, productRepo);
    }

    private SalesModel buildSalesModel() {
        return SalesModel.builder()
                .times(Set.of(new TimeModel(1L, null, null, null, null, null, null, null)))
                .stores(Set.of(new StoreModel(2L, null, null, null, null)))
                .products(Set.of(new ProductModel(3L, null, null, null, null, null)))
                .build();
    }

    @Test
    void create_ShouldSave_WhenAllEntitiesExist() {
        SalesModel model = buildSalesModel();

        when(timeRepo.findAllById(List.of(1L)))
                .thenReturn(List.of(new TimeModel(1L, null, null, null, null, null, null, null)));
        when(storeRepo.findAllById(List.of(2L)))
                .thenReturn(List.of(new StoreModel(2L, null, null, null, null)));
        when(productRepo.findAllById(List.of(3L)))
                .thenReturn(List.of(new ProductModel(3L, null, null, null, null, null)));
        when(salesRepo.save(any())).thenAnswer(inv -> inv.getArgument(0));

        SalesModel result = service.create(model);

        assertThat(result.getTimes()).hasSize(1);
        assertThat(result.getStores()).hasSize(1);
        assertThat(result.getProducts()).hasSize(1);

        verify(salesRepo).save(result);
    }

    @Test
    void create_ShouldThrow_WhenTimesMissing() {
        SalesModel model = buildSalesModel();
        when(timeRepo.findAllById(List.of(1L))).thenReturn(List.of());

        assertThatThrownBy(() -> service.create(model))
                .isInstanceOf(TimesNotFoundException.class);

        verify(salesRepo, never()).save(any());
    }

    @Test
    void create_ShouldThrow_WhenStoresMissing() {
        SalesModel model = buildSalesModel();
        when(timeRepo.findAllById(List.of(1L)))
                .thenReturn(List.of(new TimeModel(1L, null, null, null, null, null, null, null)));
        when(storeRepo.findAllById(List.of(2L))).thenReturn(List.of());

        assertThatThrownBy(() -> service.create(model))
                .isInstanceOf(StoresNotFoundException.class);

        verify(salesRepo, never()).save(any());
    }

    @Test
    void create_ShouldThrow_WhenProductsMissing() {
        SalesModel model = buildSalesModel();
        when(timeRepo.findAllById(List.of(1L)))
                .thenReturn(List.of(new TimeModel(1L, null, null, null, null, null, null, null)));
        when(storeRepo.findAllById(List.of(2L)))
                .thenReturn(List.of(new StoreModel(2L, null, null, null, null)));
        when(productRepo.findAllById(List.of(3L))).thenReturn(List.of());

        assertThatThrownBy(() -> service.create(model))
                .isInstanceOf(ProductsNotFoundException.class);

        verify(salesRepo, never()).save(any());
    }

    @Test
    void getById_ShouldReturn_WhenExists() {
        SalesModel sale = SalesModel.builder().id(10L).build();
        when(salesRepo.findById(10L)).thenReturn(Optional.of(sale));

        SalesModel result = service.getById(10L);

        assertThat(result).isEqualTo(sale);
    }

    @Test
    void getById_ShouldThrow_WhenNotFound() {
        when(salesRepo.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getById(99L))
                .isInstanceOf(SalesNotFoundException.class);
    }

    @Test
    void update_ShouldCallSave_WithUpdatedId() {
        SalesModel model = buildSalesModel();
        when(salesRepo.findById(5L)).thenReturn(Optional.of(new SalesModel()));
        when(salesRepo.save(any())).thenAnswer(inv -> inv.getArgument(0));

        SalesModel result = service.update(5L, model);

        assertThat(result.getId()).isEqualTo(5L);
        verify(salesRepo).save(model);
    }

    @Test
    void delete_ShouldCallRepositoryDeleteById() {
        service.delete(7L);
        verify(salesRepo).deleteById(7L);
    }
}
