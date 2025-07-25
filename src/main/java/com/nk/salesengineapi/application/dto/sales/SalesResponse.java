package com.nk.salesengineapi.application.dto.sales;

import com.nk.salesengineapi.application.dto.store.StoreResponse;
import com.nk.salesengineapi.application.dto.time.TimeResponse;
import com.nk.salesengineapi.application.dto.product.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesResponse {

    private Long id;

    private Set<TimeResponse> times;

    private Set<StoreResponse> stores;

    private Set<ProductResponse> products;

}
