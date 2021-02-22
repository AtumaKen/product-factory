package com.kenoly.productfactory.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenoly.productfactory.services.ProductService;
import com.kenoly.productfactory.web.model.ProductDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @MockBean
    ProductService ProductService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private ProductDto validProduct;

    @Before
    public void setUp() {
        validProduct = ProductDto.builder().id(UUID.randomUUID())
                .productName("Product1")
                .productStyle("PALE_ALE")
                .upc(123456789012L)
                .build();
    }

    @Test
    public void getProduct() throws Exception {
        given(ProductService.getProductById(any(UUID.class))).willReturn(validProduct);

        mockMvc.perform(get("/api/v1/product/" + validProduct.getId().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validProduct.getId().toString())))
                .andExpect(jsonPath("$.productName", is("Product1")));
    }

    @Test
    public void handlePost() throws Exception {
        //given
        ProductDto productDto = validProduct;
        productDto.setId(null);
        ProductDto savedDto = ProductDto.builder().id(UUID.randomUUID()).productName("New Product").build();
        String ProductDtoJson = objectMapper.writeValueAsString(productDto);

        given(ProductService.saveNewProduct(any())).willReturn(savedDto);

        mockMvc.perform(post("/api/v1/product/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ProductDtoJson))
                .andExpect(status().isCreated());

    }

    @Test
    public void handleUpdate() throws Exception {
        //given
        ProductDto ProductDto = validProduct;
        ProductDto.setId(null);
        String ProductDtoJson = objectMapper.writeValueAsString(ProductDto);

        //when
        mockMvc.perform(put("/api/v1/product/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(ProductDtoJson))
                .andExpect(status().isNoContent());

        then(ProductService).should().updateProduct(any(), any());

    }
}