package com.nk.salesengineapi.domain.model;

import lombok.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class TimeModel {

    private Long id;
    private Date date;
    private String description;
    private String monthCode;
    private String monthDescription;
    private String yearCode;
    private String weekCode;
    private String weekDescription;

    public boolean isValidMonth() {
        try {
            int monthNumber = Integer.parseInt(monthCode);
            return monthNumber >= 1 && monthNumber <= 12;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isValidYear() {
        return yearCode != null && yearCode.matches("\\d{4}");
    }

    public void generateDescriptionIfMissing() {
        if ((description == null || description.isBlank()) && date != null) {
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            this.description = "Date: " + localDate.format(DateTimeFormatter.ISO_DATE);
        }
    }

    public boolean isValidWeek() {
        try {
            int weekNum = Integer.parseInt(weekCode);
            return weekNum >= 1 && weekNum <= 53;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
