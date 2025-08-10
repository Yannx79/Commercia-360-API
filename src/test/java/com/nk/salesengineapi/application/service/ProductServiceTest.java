package com.nk.salesengineapi.application.service;
import com.nk.salesengineapi.application.port.out.ProductRepositoryPort;
import com.nk.salesengineapi.domain.exception.product.ProductNotFoundException;
import com.nk.salesengineapi.domain.model.ProductModel;
import com.nk.salesengineapi.application.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductRepositoryPort repositoryPort;
    private ProductService productService;

    @BeforeEach
    void setup() {
        repositoryPort = mock(ProductRepositoryPort.class);
        productService = new ProductService(repositoryPort);
    }

    @Test
    void create_ShouldCallSaveAndReturnProduct() {
        ProductModel product = new ProductModel();
        when(repositoryPort.save(product)).thenReturn(product);

        ProductModel result = productService.create(product);

        verify(repositoryPort).save(product);
        assertThat(result).isEqualTo(product);
    }

    @Test
    void getAll_ShouldReturnAllProducts() {
        List<ProductModel> products = List.of(new ProductModel(), new ProductModel());
        when(repositoryPort.findAll()).thenReturn(products);

        List<ProductModel> result = productService.getAll();

        verify(repositoryPort).findAll();
        assertThat(result).isEqualTo(products);
    }

    @Test
    void getById_WhenProductExists_ShouldReturnProduct() {
        ProductModel product = new ProductModel();
        when(repositoryPort.findById(1L)).thenReturn(Optional.of(product));

        ProductModel result = productService.getById(1L);

        verify(repositoryPort).findById(1L);
        assertThat(result).isEqualTo(product);
    }

    @Test
    void getById_WhenProductDoesNotExist_ShouldThrowException() {
        when(repositoryPort.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.getById(1L))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessageContaining("1");

        verify(repositoryPort).findById(1L);
    }

    @Test
    void update_WhenProductExists_ShouldSaveAndReturnUpdated() {
        ProductModel updatedProduct = new ProductModel();
        when(repositoryPort.findById(1L)).thenReturn(Optional.of(new ProductModel()));
        when(repositoryPort.save(any(ProductModel.class))).thenReturn(updatedProduct);

        ProductModel result = productService.update(1L, updatedProduct);

        verify(repositoryPort).findById(1L);
        ArgumentCaptor<ProductModel> captor = ArgumentCaptor.forClass(ProductModel.class);
        verify(repositoryPort).save(captor.capture());

        assertThat(captor.getValue().getId()).isEqualTo(1L);
        assertThat(result).isEqualTo(updatedProduct);
    }

    @Test
    void update_WhenProductDoesNotExist_ShouldThrowException() {
        ProductModel product = new ProductModel();
        when(repositoryPort.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.update(1L, product))
                .isInstanceOf(ProductNotFoundException.class);

        verify(repositoryPort).findById(1L);
        verify(repositoryPort, never()).save(any());
    }

    @Test
    void delete_ShouldCallDeleteById() {
        doNothing().when(repositoryPort).deleteById(1L);

        productService.delete(1L);

        verify(repositoryPort).deleteById(1L);
    }
}
