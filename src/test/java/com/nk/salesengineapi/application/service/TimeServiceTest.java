package com.nk.salesengineapi.application.service;

import com.nk.salesengineapi.application.port.out.ProductRepositoryPort;
import com.nk.salesengineapi.application.port.out.TimeRepositoryPort;
import com.nk.salesengineapi.domain.exception.time.TimeNotFoundException;
import com.nk.salesengineapi.domain.model.TimeModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TimeServiceTest {

    private TimeRepositoryPort repositoryPort;
    private TimeService timeService;

    @BeforeEach
    void setup() {
        repositoryPort = mock(TimeRepositoryPort.class);
        timeService = new TimeService(repositoryPort);
    }

    @Test
    void create_ShouldCallSaveAndReturnTime() {
        TimeModel time = new TimeModel();
        when(repositoryPort.save(time)).thenReturn(time);

        TimeModel result = timeService.create(time);
        verify(repositoryPort).save(time);
        assertThat(result).isEqualTo(time);
    }

    @Test
    void getAll_ShouldReturnAllTimes() {
        List<TimeModel> times = List.of(new TimeModel(), new TimeModel());
        when(repositoryPort.findAll()).thenReturn(times);

        List<TimeModel> result = timeService.getAll();

        verify(repositoryPort).findAll();
        assertThat(result).isEqualTo(times);
    }

    @Test
    void getById_WhenTimeExists_ShouldReturnTime() {
        TimeModel time = new TimeModel();
        when(repositoryPort.findById(1L)).thenReturn(Optional.of(time));

        TimeModel result = timeService.getById(1L);

        verify(repositoryPort).findById(1L);
        assertThat(result).isEqualTo(time);
    }

    @Test
    void getById_WhenTimeDoesNotExist_ShouldThrowException() {
        when(repositoryPort.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> timeService.getById(1L))
                .isInstanceOf(TimeNotFoundException.class)
                .hasMessageContaining("1");

        verify(repositoryPort).findById(1L);
    }

    @Test
    void update_WhenTimeExists_ShouldSaveAndReturnUpdated() {
        TimeModel updatedTime = new TimeModel();
        when(repositoryPort.findById(1L)).thenReturn(Optional.of(new TimeModel()));
        when(repositoryPort.save(any(TimeModel.class))).thenReturn(updatedTime);

        TimeModel result = timeService.update(1L, updatedTime);

        verify(repositoryPort).findById(1L);
        ArgumentCaptor<TimeModel> captor = ArgumentCaptor.forClass(TimeModel.class);
        verify(repositoryPort).save(captor.capture());

        assertThat(captor.getValue()).isEqualTo(updatedTime);
        assertThat(result).isEqualTo(updatedTime);
    }

    @Test
    void update_WhenTimeDoesNotExist_ShouldThrowException() {
        TimeModel time = new TimeModel();
        when(repositoryPort.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> timeService.update(1L, time))
            .isInstanceOf(TimeNotFoundException.class);
        verify(repositoryPort).findById(1L);
        verify(repositoryPort, never()).save(any());
    }

    @Test
    void delete_ShouldCallDeleteById() {
        doNothing().when(repositoryPort).deleteById(1L);
        timeService.delete(1L);
        verify(repositoryPort).deleteById(1L);
    }

}
