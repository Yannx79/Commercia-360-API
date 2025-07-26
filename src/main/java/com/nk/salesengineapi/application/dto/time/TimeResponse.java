package com.nk.salesengineapi.application.dto.time;

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
    private String monthCode;
    private String monthDescription;
    private String yearCode;
    private String weekCode;
    private String weekDescription;

}
