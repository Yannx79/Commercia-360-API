package com.nk.salesengineapi.application.dto.sales;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Data
public class SalesRequest {

    @NotEmpty(message = "At least one time ID is required")
    private Set<Long> timeIds;

    @NotEmpty(message = "At least one store ID is required")
    private Set<Long> storeIds;

    @NotEmpty(message = "At least one product ID is required")
    private Set<Long> productIds;

}
