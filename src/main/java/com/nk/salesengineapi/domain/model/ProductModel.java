package com.nk.salesengineapi.domain.model;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProductModel {

    private Long id;
    private String description;
    private String category;
    private String categoryDescription;
    private Double unitPrice;
    private Date created;

    public boolean isPremium() {
        return unitPrice != null &&
                unitPrice > 1000 &&
                "Electronics".equalsIgnoreCase(category);
    }

    public void applyDiscount(double percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Invalid discount");
        }
        this.unitPrice = unitPrice - (unitPrice * percentage / 100);
    }

}
