package com.nk.salesengineapi.infraestructure.web.controller;

import com.nk.salesengineapi.application.dto.product.ProductRequest;
import com.nk.salesengineapi.application.port.in.ProductUseCase;
import com.nk.salesengineapi.infrastructure.web.controller.ProductController;
import com.nk.salesengineapi.infrastructure.web.mapper.ProductDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nk.salesengineapi.application.dto.product.ProductResponse;
import com.nk.salesengineapi.domain.model.ProductModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    private static final Long PRODUCT_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProductUseCase productUseCase;

    @MockitoBean
    private ProductDtoMapper modelMapper;

    // ======== TEST CASES ========

    @Test
    void givenProductsExist_whenGetAll_thenReturnProductList() throws Exception {
        ProductModel model1 = buildProductModel(1L, "Laptop", "Electronics", "Portable Computer", 1200.0);
        ProductModel model2 = buildProductModel(2L, "Mouse", "Accessories", "Wireless Mouse", 25.0);

        ProductResponse response1 = buildProductResponse(model1);
        ProductResponse response2 = buildProductResponse(model2);

        Mockito.when(productUseCase.getAll()).thenReturn(List.of(model1, model2));
        Mockito.when(modelMapper.toResponse(model1)).thenReturn(response1);
        Mockito.when(modelMapper.toResponse(model2)).thenReturn(response2);

        mockMvc.perform(get("/api/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].description").value("Laptop"))
                .andExpect(jsonPath("$.data[0].category").value("Electronics"))
                .andExpect(jsonPath("$.data[1].id").value(2))
                .andExpect(jsonPath("$.data[1].description").value("Mouse"));
    }

    @Test
    void givenProductExists_whenGetById_thenReturnProduct() throws Exception {
        ProductModel model = buildProductModel(PRODUCT_ID, "Laptop Gamer", "Electronics", "High-end gaming laptop", 2500.0);
        ProductResponse response = buildProductResponse(model);

        Mockito.when(productUseCase.getById(anyLong())).thenReturn(model);
        Mockito.when(modelMapper.toResponse(model)).thenReturn(response);

        mockMvc.perform(get("/api/products/{id}", PRODUCT_ID).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.id").value(PRODUCT_ID))
                .andExpect(jsonPath("$.data.description").value("Laptop Gamer"));
    }

    @Test
    void givenValidRequest_whenCreateProduct_thenReturnCreatedProduct() throws Exception {
        ProductRequest request = buildProductRequest("Laptop", "Electronics", "Portable Computer", 1500.0);
        ProductModel model = buildProductModel(PRODUCT_ID, request.getDescription(), request.getCategory(), request.getCategoryDescription(), request.getUnitPrice());
        ProductResponse response = buildProductResponse(model);

        Mockito.when(modelMapper.toDomain(any(ProductRequest.class))).thenReturn(model);
        Mockito.when(productUseCase.create(any(ProductModel.class))).thenReturn(model);
        Mockito.when(modelMapper.toResponse(any(ProductModel.class))).thenReturn(response);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", URI.create("/api/products/" + PRODUCT_ID).toString()))
                .andExpect(jsonPath("$.data.id").value(PRODUCT_ID))
                .andExpect(jsonPath("$.data.description").value("Laptop"));
    }

    @Test
    void givenValidRequest_whenUpdateProduct_thenReturnUpdatedProduct() throws Exception {
        ProductRequest request = buildProductRequest("Laptop Pro", "Electronics", "High-end Portable Computer", 2000.0);
        ProductModel model = buildProductModel(PRODUCT_ID, request.getDescription(), request.getCategory(), request.getCategoryDescription(), request.getUnitPrice());
        ProductResponse response = buildProductResponse(model);

        Mockito.when(modelMapper.toDomain(any(ProductRequest.class))).thenReturn(model);
        Mockito.when(productUseCase.update(eq(PRODUCT_ID), any(ProductModel.class))).thenReturn(model);
        Mockito.when(modelMapper.toResponse(any(ProductModel.class))).thenReturn(response);

        mockMvc.perform(put("/api/products/{id}", PRODUCT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.description").value("Laptop Pro"));
    }

    @Test
    void givenProductId_whenDeleteProduct_thenReturnNoContent() throws Exception {
        Mockito.doNothing().when(productUseCase).delete(PRODUCT_ID);

        mockMvc.perform(delete("/api/products/{id}", PRODUCT_ID))
                .andExpect(status().isNoContent());

        Mockito.verify(productUseCase, Mockito.times(1)).delete(PRODUCT_ID);
    }

    // ======== HELPER METHODS ========

    private ProductModel buildProductModel(Long id, String desc, String category, String catDesc, Double price) {
        return ProductModel.builder()
                .id(id)
                .description(desc)
                .category(category)
                .categoryDescription(catDesc)
                .unitPrice(price)
                .build();
    }

    private ProductResponse buildProductResponse(ProductModel model) {
        return ProductResponse.builder()
                .id(model.getId())
                .description(model.getDescription())
                .category(model.getCategory())
                .categoryDescription(model.getCategoryDescription())
                .unitPrice(model.getUnitPrice())
                .build();
    }

    private ProductRequest buildProductRequest(String desc, String category, String catDesc, Double price) {
        return new ProductRequest(desc, category, catDesc, price);
    }
}