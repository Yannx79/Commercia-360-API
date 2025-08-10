package com.nk.salesengineapi.domain.model;

import com.nk.salesengineapi.domain.model.StoreModel;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.*;

class StoreModelTest {

    @Test
    void isInRegion_ShouldReturnTrue_WhenRegionMatchesIgnoringCase() {
        StoreModel store = StoreModel.builder()
                .region("Lima")
                .build();

        assertThat(store.isInRegion("lima")).isTrue();
        assertThat(store.isInRegion("LIMA")).isTrue();
    }

    @Test
    void isInRegion_ShouldReturnFalse_WhenRegionDoesNotMatchOrIsNull() {
        StoreModel store = StoreModel.builder()
                .region("Arequipa")
                .build();

        assertThat(store.isInRegion("Lima")).isFalse();
        assertThat(store.isInRegion(null)).isFalse();

        StoreModel nullRegionStore = StoreModel.builder()
                .region(null)
                .build();

        assertThat(nullRegionStore.isInRegion("Lima")).isFalse();
    }

    @Test
    void getFullDescription_ShouldConcatenateDescriptionAndRegionName() {
        StoreModel store = StoreModel.builder()
                .description("Store A")
                .regionName("Central")
                .build();

        assertThat(store.getFullDescription()).isEqualTo("Store A - Central");
    }

    @Test
    void isRecentlyCreated_ShouldReturnTrue_WhenCreatedWithinLast30Days() {
        long now = System.currentTimeMillis();
        Date recentDate = new Date(now - (1000L * 60 * 60 * 24 * 10)); // 10 days ago

        StoreModel store = StoreModel.builder()
                .created(recentDate)
                .build();

        assertThat(store.isRecentlyCreated()).isTrue();
    }

    @Test
    void isRecentlyCreated_ShouldReturnFalse_WhenCreatedMoreThan30DaysAgoOrNull() {
        long now = System.currentTimeMillis();
        Date oldDate = new Date(now - (1000L * 60 * 60 * 24 * 40)); // 40 days ago

        StoreModel oldStore = StoreModel.builder()
                .created(oldDate)
                .build();

        StoreModel nullDateStore = StoreModel.builder()
                .created(null)
                .build();

        assertThat(oldStore.isRecentlyCreated()).isFalse();
        assertThat(nullDateStore.isRecentlyCreated()).isFalse();
    }
}
