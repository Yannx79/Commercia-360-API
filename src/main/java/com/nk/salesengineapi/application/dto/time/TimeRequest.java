package com.nk.salesengineapi.application.dto.time;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeRequest {

    @NotNull(message = "Date is required")
    private Date date;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Month is required")
    private String monthCode;

    private String monthDescription;

    @NotBlank(message = "Year is required")
    private String yearCode;

    private String weekCode;

    private String weekDescription;

}
