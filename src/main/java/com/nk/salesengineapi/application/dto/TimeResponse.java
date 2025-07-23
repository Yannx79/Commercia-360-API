package com.nk.salesengineapi.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeResponse {

    private Long id;
    private Date date;
    private String description;
    private String month;
    private String monthDescription;
    private String year;
    private String week;
    private String weekDescription;

}
