package com.kenoly.productfactory.services.v2;

import com.kenoly.productfactory.web.model.v2.ProductDtoV2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceV2Impl implements ProductServiceV2 {
    @Override
    public ProductDtoV2 getProductById(UUID productId) {
        return null;
    }

    @Override
    public ProductDtoV2 saveNewProduct(ProductDtoV2 productDto) {
        return null;
    }

    @Override
    public void updateProduct(UUID productId, ProductDtoV2 productDto) {

    }

    @Override
    public void deleteById(UUID productId) {

    }
}
