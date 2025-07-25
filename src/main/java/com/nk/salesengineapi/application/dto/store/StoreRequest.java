package com.nk.salesengineapi.application.dto.store;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreRequest {

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Region is required")
    private String region;

    private String regionName;

}
