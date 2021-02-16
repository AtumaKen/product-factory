package com.kenoly.productfactory.services;

import com.kenoly.productfactory.web.model.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Override
    public ProductDto getProductById(UUID productId) {
        return ProductDto.builder().id(UUID.randomUUID())
                .productName("Galaxy Cat")
                .productStyle("Pale Ale")
                .build();
    }

    @Override
    public ProductDto saveNewProduct(ProductDto productDto) {
        return ProductDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateProduct(UUID ProductId, ProductDto productDto) {
        //todo impl - would add a real impl to update Product
    }

    @Override
    public void deleteById(UUID ProductId) {
        log.debug("Deleting a Product...");
    }
}
