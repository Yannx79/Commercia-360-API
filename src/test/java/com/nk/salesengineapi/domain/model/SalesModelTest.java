package com.nk.salesengineapi.domain.model;
import com.nk.salesengineapi.domain.model.SalesModel;
import com.nk.salesengineapi.domain.model.TimeModel;
import com.nk.salesengineapi.domain.model.StoreModel;
import com.nk.salesengineapi.domain.model.ProductModel;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SalesModelTest {

    @Test
    void isValidSale_ShouldReturnTrue_WhenAllSetsNotEmpty() {
        SalesModel sale = SalesModel.builder()
                .times(Set.of(new TimeModel()))
                .stores(Set.of(new StoreModel()))
                .products(Set.of(new ProductModel()))
                .build();

        assertThat(sale.isValidSale()).isTrue();
    }

    @Test
    void isValidSale_ShouldReturnFalse_WhenAnySetIsEmpty() {
        SalesModel saleEmptyTimes = SalesModel.builder()
                .times(Set.of())
                .stores(Set.of(new StoreModel()))
                .products(Set.of(new ProductModel()))
                .build();
        SalesModel saleEmptyStores = SalesModel.builder()
                .times(Set.of(new TimeModel()))
                .stores(Set.of())
                .products(Set.of(new ProductModel()))
                .build();
        SalesModel saleEmptyProducts = SalesModel.builder()
                .times(Set.of(new TimeModel()))
                .stores(Set.of(new StoreModel()))
                .products(Set.of())
                .build();

        assertThat(saleEmptyTimes.isValidSale()).isFalse();
        assertThat(saleEmptyStores.isValidSale()).isFalse();
        assertThat(saleEmptyProducts.isValidSale()).isFalse();
    }

    @Test
    void getTotalProducts_ShouldReturnCorrectCount() {
        SalesModel sale = SalesModel.builder()
                .products(Set.of(ProductModel.builder().id(1L).build(), ProductModel.builder().id(2L).build()))
                .build();

        assertThat(sale.getTotalProducts()).isEqualTo(2);
    }

    @Test
    void isSingleStoreSale_ShouldReturnTrue_WhenOneStore() {
        SalesModel sale = SalesModel.builder()
                .stores(Set.of(new StoreModel()))
                .build();

        assertThat(sale.isSingleStoreSale()).isTrue();
    }

    @Test
    void isSingleStoreSale_ShouldReturnFalse_WhenMultipleStores() {
        SalesModel sale = SalesModel.builder()
                .stores(Set.of(StoreModel.builder().id(1L).build(), StoreModel.builder().id(2L).build()))
                .build();

        assertThat(sale.isSingleStoreSale()).isFalse();
    }
}
