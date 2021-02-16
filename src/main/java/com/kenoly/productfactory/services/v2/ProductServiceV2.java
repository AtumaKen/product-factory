package com.kenoly.productfactory.services.v2;

import com.kenoly.productfactory.web.model.v2.ProductDtoV2;

import java.util.UUID;

public interface ProductServiceV2 {
    ProductDtoV2 getProductById(UUID ProductId);

    ProductDtoV2 saveNewProduct(ProductDtoV2 productDto);

    void updateProduct(UUID productId, ProductDtoV2 productDto);

    void deleteById(UUID productId);
}
