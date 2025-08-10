package com.nk.salesengineapi.domain.model;

import com.nk.salesengineapi.domain.model.TimeModel;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.*;

class TimeModelTest {

    @Test
    void isValidMonth_ShouldReturnTrue_ForValidMonths() {
        for (int i = 1; i <= 12; i++) {
            TimeModel time = TimeModel.builder().monthCode(String.valueOf(i)).build();
            assertThat(time.isValidMonth()).isTrue();
        }
    }

    @Test
    void isValidMonth_ShouldReturnFalse_ForInvalidMonths() {
        TimeModel negative = TimeModel.builder().monthCode("-1").build();
        TimeModel zero = TimeModel.builder().monthCode("0").build();
        TimeModel thirteen = TimeModel.builder().monthCode("13").build();
        TimeModel nonNumeric = TimeModel.builder().monthCode("abc").build();

        assertThat(negative.isValidMonth()).isFalse();
        assertThat(zero.isValidMonth()).isFalse();
        assertThat(thirteen.isValidMonth()).isFalse();
        assertThat(nonNumeric.isValidMonth()).isFalse();
    }

    @Test
    void isValidYear_ShouldReturnTrue_ForValidYears() {
        TimeModel time = TimeModel.builder().yearCode("2025").build();
        assertThat(time.isValidYear()).isTrue();
    }

    @Test
    void isValidYear_ShouldReturnFalse_ForInvalidYears() {
        TimeModel nullYear = TimeModel.builder().yearCode(null).build();
        TimeModel shortYear = TimeModel.builder().yearCode("123").build();
        TimeModel longYear = TimeModel.builder().yearCode("12345").build();
        TimeModel letters = TimeModel.builder().yearCode("20a5").build();

        assertThat(nullYear.isValidYear()).isFalse();
        assertThat(shortYear.isValidYear()).isFalse();
        assertThat(longYear.isValidYear()).isFalse();
        assertThat(letters.isValidYear()).isFalse();
    }

    @Test
    void generateDescriptionIfMissing_ShouldSetDescription_WhenBlankAndDatePresent() {
        Date now = new Date();
        TimeModel time = TimeModel.builder()
                .date(now)
                .description("   ")  // blank string
                .build();

        time.generateDescriptionIfMissing();

        assertThat(time.getDescription()).startsWith("Date:");
    }

    @Test
    void generateDescriptionIfMissing_ShouldNotOverwriteDescription_WhenNotBlank() {
        TimeModel time = TimeModel.builder()
                .description("Already set")
                .build();

        time.generateDescriptionIfMissing();

        assertThat(time.getDescription()).isEqualTo("Already set");
    }

    @Test
    void generateDescriptionIfMissing_ShouldNotSetDescription_WhenDateNull() {
        TimeModel time = TimeModel.builder()
                .description(null)
                .date(null)
                .build();

        time.generateDescriptionIfMissing();

        assertThat(time.getDescription()).isNull();
    }

    @Test
    void isValidWeek_ShouldReturnTrue_ForValidWeeks() {
        TimeModel time = TimeModel.builder().weekCode("1").build();
        assertThat(time.isValidWeek()).isTrue();

        time.setWeekCode("53");
        assertThat(time.isValidWeek()).isTrue();
    }

    @Test
    void isValidWeek_ShouldReturnFalse_ForInvalidWeeks() {
        TimeModel negative = TimeModel.builder().weekCode("-1").build();
        TimeModel zero = TimeModel.builder().weekCode("0").build();
        TimeModel fiftyFour = TimeModel.builder().weekCode("54").build();
        TimeModel nonNumeric = TimeModel.builder().weekCode("abc").build();

        assertThat(negative.isValidWeek()).isFalse();
        assertThat(zero.isValidWeek()).isFalse();
        assertThat(fiftyFour.isValidWeek()).isFalse();
        assertThat(nonNumeric.isValidWeek()).isFalse();
    }
}
