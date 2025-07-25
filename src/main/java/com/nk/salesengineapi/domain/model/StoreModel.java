package com.nk.salesengineapi.domain.model;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class StoreModel {

    private Long id;
    private String description;
    private String region;
    private String regionName;
    private Date created;

    public boolean isInRegion(String targetRegion) {
        return region != null && region.equalsIgnoreCase(targetRegion);
    }

    public String getFullDescription() {
        return description + " - " + regionName;
    }

    public boolean isRecentlyCreated() {
        if (created == null) return false;
        long millisIn30Days = 1000L * 60 * 60 * 24 * 30;
        return System.currentTimeMillis() - created.getTime() < millisIn30Days;
    }

}
