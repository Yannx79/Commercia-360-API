package com.nk.salesengineapi.application.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public class SalesRequest {

    @NotEmpty(message = "At least one time ID is required")
    private Set<Long> timeIds;

    @NotEmpty(message = "At least one store ID is required")
    private Set<Long> storeIds;

    @NotEmpty(message = "At least one product ID is required")
    private Set<Long> productIds;

}
