package com.nk.salesengineapi.domain.model;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ProductModelTest {

    @Test
    void isPremium_ShouldReturnTrue_ForElectronicsAbove1000() {
        ProductModel product = ProductModel.builder()
                .unitPrice(1500.0)
                .category("Electronics")
                .build();

        assertThat(product.isPremium()).isTrue();
    }

    @Test
    void isPremium_ShouldReturnFalse_ForElectronicsBelowOrEqual1000() {
        ProductModel product = ProductModel.builder()
                .unitPrice(1000.0)
                .category("Electronics")
                .build();

        assertThat(product.isPremium()).isFalse();
    }

    @Test
    void isPremium_ShouldReturnFalse_ForNonElectronics() {
        ProductModel product = ProductModel.builder()
                .unitPrice(1500.0)
                .category("Furniture")
                .build();

        assertThat(product.isPremium()).isFalse();
    }

    @Test
    void applyDiscount_ShouldReducePriceCorrectly() {
        ProductModel product = ProductModel.builder()
                .unitPrice(200.0)
                .build();

        product.applyDiscount(25);

        assertThat(product.getUnitPrice()).isEqualTo(150.0);
    }

    @Test
    void applyDiscount_ShouldThrowException_WhenNegativeDiscount() {
        ProductModel product = ProductModel.builder()
                .unitPrice(200.0)
                .build();

        assertThatThrownBy(() -> product.applyDiscount(-10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid discount");
    }

    @Test
    void applyDiscount_ShouldThrowException_WhenDiscountAbove100() {
        ProductModel product = ProductModel.builder()
                .unitPrice(200.0)
                .build();

        assertThatThrownBy(() -> product.applyDiscount(110))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid discount");
    }
}
