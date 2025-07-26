package com.nk.salesengineapi.domain.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class SalesModel {

    private Long id;
    private Set<TimeModel> times = new HashSet<>();
    private Set<StoreModel> stores = new HashSet<>();
    private Set<ProductModel> products = new HashSet<>();

    public boolean isValidSale() {
        return !times.isEmpty() && !stores.isEmpty() && !products.isEmpty();
    }

    public int getTotalProducts() {
        return products.size();
    }

    public boolean isSingleStoreSale() {
        return stores.size() == 1;
    }

}
