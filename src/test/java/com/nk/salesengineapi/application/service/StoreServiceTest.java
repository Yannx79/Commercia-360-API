package com.nk.salesengineapi.application.service;

import com.nk.salesengineapi.application.port.out.StoreRepositoryPort;
import com.nk.salesengineapi.domain.exception.store.StoreNotFoundException;
import com.nk.salesengineapi.domain.model.StoreModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class StoreServiceTest {

    private StoreRepositoryPort repositoryPort;
    private StoreService storeService;

    @BeforeEach
    void setUp() {
        repositoryPort = mock(StoreRepositoryPort.class);
        storeService = new StoreService(repositoryPort);
    }

    @Test
    void create_ShouldReturnSavedStore() {
        StoreModel store = new StoreModel();
        when(repositoryPort.save(store)).thenReturn(store);

        StoreModel result = storeService.create(store);

        assertThat(result).isEqualTo(store);
        verify(repositoryPort).save(store);
    }

    @Test
    void getAll_ShouldReturnListOfStores() {
        List<StoreModel> stores = List.of(new StoreModel());
        when(repositoryPort.findAll()).thenReturn(stores);

        List<StoreModel> result = storeService.getAll();

        assertThat(result).hasSize(1);
        verify(repositoryPort).findAll();
    }

    @Test
    void getById_ShouldReturnStore_WhenExists() {
        StoreModel store = new StoreModel();
        when(repositoryPort.findById(1L)).thenReturn(Optional.of(store));

        StoreModel result = storeService.getById(1L);

        assertThat(result).isEqualTo(store);
        verify(repositoryPort).findById(1L);
    }

    @Test
    void getById_ShouldThrowException_WhenNotFound() {
        when(repositoryPort.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> storeService.getById(1L))
                .isInstanceOf(StoreNotFoundException.class)
                .hasMessageContaining("1");

        verify(repositoryPort).findById(1L);
    }

    @Test
    void update_ShouldReplaceIdAndSave() {
        StoreModel store = new StoreModel();
        when(repositoryPort.findById(1L)).thenReturn(Optional.of(new StoreModel()));
        when(repositoryPort.save(any())).thenReturn(new StoreModel());

        StoreModel result = storeService.update(1L, store);

        ArgumentCaptor<StoreModel> captor = ArgumentCaptor.forClass(StoreModel.class);
        verify(repositoryPort).save(captor.capture());
        assertThat(captor.getValue().getId()).isEqualTo(1L);
    }

    @Test
    void delete_ShouldCallRepositoryDeleteById() {
        storeService.delete(1L);
        verify(repositoryPort).deleteById(1L);
    }
}
